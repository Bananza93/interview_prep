package lesson_1.task_3.shapes;

public abstract class Shape {

    public abstract double getShapeSquare();

    public String getShapeType() {
        return this.getClass().getSimpleName() + " from Shape class";
    }
}
