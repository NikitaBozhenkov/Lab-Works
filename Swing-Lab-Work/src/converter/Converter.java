package converter;

import geometricFigures.GeometricFigure;
import shapes.*;
import geometricFigures.Circle;
import geometricFigures.GeometricFigure;

import java.util.Map;
import java.util.function.BiFunction;

public class Converter {
    public <U extends GeometricFigure> Shape convert(U figure) {
        switch (figure.getClass().getName()) {
            case "twoDimensionFigures.Circle" : {
                if (figure.getRadius() > 10) {
                    return new Sphere(figure.getRadius());
                } else {
                    return new Cylinder(figure.getRadius(), 10);
                }
            }
            case "twoDimensionFigures.Triangle" : {
                return new Pyramid(figure.getSide(), figure.getHeight(), 10);
            }
            case "twoDimensionFigures.Square" : {
                return new Cube(figure.getSide());
            }
            default: return new Sphere(0);
        }
    }
}
