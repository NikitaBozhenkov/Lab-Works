package shapes;

public abstract class ConvexFigure extends Shape {
    private double area;
    private double side;

    public double getArea() {
        return area;
    }

    public double getSide() {
        return side;
    }

    public abstract String getBaseFigure();

    ConvexFigure(double volume, double area, double side, String className) {
        super(volume, className);
        this.area = area;
        this.side = side;
    }
}
