package lesson_1.task_3.shapes;

public class Square extends Shape {

    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getShapeSquare() {
        return Math.pow(sideLength, 2);
    }
}
