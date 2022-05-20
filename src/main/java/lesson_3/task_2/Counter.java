package lesson_3.task_2;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private long count = 0;
    private static final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        count++;
        lock.unlock();
    }

    public void decrement() {
        lock.lock();
        count--;
        lock.unlock();
    }

    public long getCount() {
        return count;
    }
}
