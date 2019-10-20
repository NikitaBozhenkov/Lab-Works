package shapes;

import exceptions.NegativeVolumeException;

public class Sphere extends RotationFigure {
    public Sphere(double radius) {
        super(4*3.14*radius*radius*radius/3, 3.14*radius*radius, radius, "Sphere");
        if (radius < 0) throw new NegativeVolumeException("Can't create figure with negative volume");
    }
}
