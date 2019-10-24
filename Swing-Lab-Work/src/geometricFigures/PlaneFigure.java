package geometricFigures;

import figure.Figure;

public abstract class PlaneFigure extends Figure implements Comparable<PlaneFigure> {
    private double firstField;
    private double secondField;
    private double area;

    public double getFirstField() {
        return firstField;
    }

    public double getArea() {
        return area;
    }

    public double getSecondField() {
        return secondField;
    }

    @Override
    public String toString() {
        return getClassName();
    }

    PlaneFigure(double firstField, double secondField, double area, String className) {
        super(className);
        this.firstField = firstField;
        this.secondField = secondField;
        this.area = area;
    }

    @Override
    public int compareTo(PlaneFigure o) {
        return Double.compare(area, o.getArea());
    }

    @Override
    public String getDimension() {
        return "Two";
    }
}
