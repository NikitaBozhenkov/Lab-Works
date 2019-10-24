package geometricFigures;

import figure.Figure;

public abstract class GeometricFigure extends Figure {
    private double parameter1;
    private double parameter2;
    private double area;

    public double getFirstParameter() {
        return parameter1;
    }

    public double getArea() {
        return area;
    }

    public double getSecondParameter() {
        return parameter2;
    }

    @Override
    public String toString() {
        return getClassName();
    }

    GeometricFigure(double parameter1, double parameter2, double area, String className) {
        super(className);
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.area = area;
    }

    @Override
    public String getDimension() {
        return "Two";
    }
}
