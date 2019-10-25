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

    public abstract String getRotatedFigure();

    RotationFigure(double volume, double area, double radius, String className) {
        super(volume, className);
        this.area = area;
        this.radius = radius;
    }
}
