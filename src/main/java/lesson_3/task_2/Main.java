package lesson_3.task_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 5;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        Counter counter = new Counter();
        for (int i = 0; i < numberOfThreads; i++) {
            if (i % 2 == 0) {
                service.execute(() -> {
                    for (int j = 0; j < 50; j++) {
                        counter.increment();
                    }
                });
            } else {
                service.execute(() -> {
                    for (int j = 0; j < 50; j++) {
                        counter.decrement();
                    }
                });
            }
        }
        Thread.sleep(1000);
        service.shutdown();
        System.out.println("Count: " + counter.getCount());
    }
}
