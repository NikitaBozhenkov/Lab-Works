package converter;

import bag.Bag;
import geometricFigures.PlaneFigure;
import shapes.*;

import java.util.Random;

public class Converter {
    public <U extends PlaneFigure> Shape convert(U figure, Bag bag) {
        Random random = new Random();
        if (figure.getArea() > bag.getVolume() * 100) {
            throw new RuntimeException("Too big figure to create");
        }
        else if (figure.getArea() < 0.01) {
            throw new RuntimeException("Too small figure to create");
        }
        double height = Math.round((Math.random()*(bag.getVolume()/figure.getArea()+0.999) + 0.001) * 100) /100;
        switch (figure.toString()) {
            case "Circle" : {
                double threshhold = 2.0;
                if (random.nextDouble() < threshhold) {
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
