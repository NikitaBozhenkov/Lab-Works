package converter;

import bag.Bag;
import exceptions.VolumeException;
import geometricFigures.GeometricFigure;
import shapes.*;

import java.util.Random;

public class Converter {
    public <U extends GeometricFigure> Shape convert(U figure, Bag bag) {
        Random random = new Random();
        if (figure.getSquare() > bag.getVolume() * 100) throw new VolumeException("Too big figure to create");
        else if (figure.getSquare() < 0.01) throw new VolumeException("Too small figure to create");
        double height = Math.round((Math.random()*(bag.getVolume()/figure.getSquare()+0.999) + 0.001) * 100) /100;
        switch (figure.toString()) {
            case "Circle" : {
                if (random.ints(0,5).findFirst().getAsInt() < 2) {
                    return new Sphere(figure.getRadius());
                } else {
                    return new Cylinder(figure.getRadius(), height);
                }
            }
            case "Triangle" : {
                return new Pyramid(figure.getSide(), figure.getHeight(), height);
            }
            case "Square" : {
                return new Cube(figure.getSide());
            }
            default: return new Sphere(0);
        }
    }
}
