package geometricFigures;

import figure.Figure;

public abstract class GeometricFigure extends Figure {
    private double side1;
    private double side2;

    public double getSide() {
        return side1;
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

    GeometricFigure(double side1, double side2, String className) {
        super(className);
        this.side1 = side1;
        this.side2 = side2;
    }
}
