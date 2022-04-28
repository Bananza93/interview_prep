package lesson_1.task_3.shapes;

public class Circle extends Shape{

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getShapeSquare() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}
