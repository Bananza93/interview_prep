package lesson_1.task_3;

import lesson_1.task_3.shapes.Circle;
import lesson_1.task_3.shapes.Shape;
import lesson_1.task_3.shapes.Square;
import lesson_1.task_3.shapes.Triangle;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Shape> shapes = List.of(
                new Square(10),
                new Triangle(4, 5, 6),
                new Circle(2)
        );

        shapes.forEach(s -> System.out.println("Square of " + s.getShapeType() + " = " + s.getShapeSquare()));
    }
}
