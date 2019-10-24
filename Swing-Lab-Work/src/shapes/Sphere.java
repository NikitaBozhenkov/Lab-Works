package shapes;

import exceptions.VolumeException;

public class Sphere extends RotationFigure {
    public Sphere(double radius) {
        super(4*3.14*radius*radius*radius/3, 3.14*radius*radius, radius, "Sphere");
        if (radius < 0) {
            throw new VolumeException("Can't create figure with negative volume", 1);
        }
    }
}
