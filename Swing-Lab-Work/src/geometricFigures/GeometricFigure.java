package geometricFigures;

import shapes.Cube;
import shapes.Pyramid;
import shapes.Shape;

import java.util.Map;
import java.util.function.BiFunction;

public abstract class GeometricFigure {
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

    GeometricFigure(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }
}
