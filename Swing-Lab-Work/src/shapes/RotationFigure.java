package shapes;

public abstract class RotationFigure extends Shape {
    private double area;
    private double radius;

    public double getArea() {
        return area;
    }

    public double getRadius() {
        return radius;
    }

    RotationFigure(double volume, double area, double radius) {
        super(volume);
        this.area = area;
        this.radius = radius;
    }
}
