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

    ConvexFigure(double volume, double area, double side) {
        super(volume);
        this.area = area;
        this.side = side;
    }
}
