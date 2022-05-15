package lesson_3.task_2;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int count = 0;
    private static final ReentrantLock lock = new ReentrantLock();

    public int increment() {
        lock.lock();
        count++;
        lock.unlock();
        return count;
    }

    public int decrement() {
        lock.lock();
        count--;
        lock.unlock();
        return count;
    }

    public int getCount() {
        return count;
    }
}
