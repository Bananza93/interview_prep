package lesson_3.task_1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Game implements Runnable {

    private static boolean flag = true;
    private static final CountDownLatch cdl = new CountDownLatch(2);
    private static final ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        // Из задания не понятно, но добавил ожидание создания обоих потоков
        try {
            cdl.countDown();
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            lock.lock();
            if (flag)
                System.out.println(Thread.currentThread().getName() + ": PING");
            else
                System.out.println(Thread.currentThread().getName() + ": PONG");
            flag = !flag;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }
}
