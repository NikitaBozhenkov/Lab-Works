package geometricFigures;

import figure.Figure;

public abstract class GeometricFigure extends Figure {
    private double side1;
    private double side2;
    private double square;

    public double getSide() {
        return side1;
    }

    public double getSquare() {
        return square;
    }

    public double getRadius() {
        return side1;
    }

    public double getHeight() {
        return side2;
    }

    @Override
    public String toString() {
        return getClassName();
    }

    GeometricFigure(double side1, double side2, double square, String className) {
        super(className);
        this.side1 = side1;
        this.side2 = side2;
        this.square = square;
    }
}
