package converter;

import planeFigures.PlaneFigure;
import shapes.*;

import java.util.Random;

public class Converter {
    public <U extends PlaneFigure> Shape convert(U figure, double volume) {
        Random random = new Random();
        if (figure.getArea() > volume * 100) {
            throw new RuntimeException("Too big figure to create");
        }
        else if (figure.getArea() < 0.01) {
            throw new RuntimeException("Too small figure to create");
        }
        double height = (double)Math.round((Math.random()*(volume/figure.getArea()+0.999) + 0.001) * 100) /100;
        switch (figure.getClassName()) {
            case "Circle" : {
                double threshold = 0.5;
                if (random.nextDouble() < threshold) {
                    return new Sphere(figure.getFirstField());
                } else {
                    return new Cylinder(figure.getFirstField(), height);
                }
            }
            case "Triangle" : {
                return new Pyramid(figure.getFirstField(), figure.getSecondField(), height);
            }
            case "Square" : {
                return new Cube(figure.getFirstField());
            }
            default: throw new RuntimeException("Can't convert this figure");
        }
    }
}
