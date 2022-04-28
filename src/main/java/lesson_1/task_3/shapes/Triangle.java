package lesson_1.task_3.shapes;

public class Triangle extends Shape {

    private final double aSideLength;
    private final double bSideLength;
    private final double cSideLength;

    public Triangle(double aSideLength, double bSideLength, double cSideLength) {
        this.aSideLength = aSideLength;
        this.bSideLength = bSideLength;
        this.cSideLength = cSideLength;
    }

    @Override
    public double getShapeSquare() {
        double p = (aSideLength + bSideLength + cSideLength) / 2;
        return Math.sqrt(p * (p - aSideLength) * (p - bSideLength) * (p - cSideLength));
    }

    @Override
    public String getShapeType() {
        return this.getClass().getSimpleName() + " from Triangle class";
    }
}
