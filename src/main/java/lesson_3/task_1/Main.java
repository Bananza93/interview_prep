package lesson_3.task_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new Game());
        service.execute(new Game());
    }
}
