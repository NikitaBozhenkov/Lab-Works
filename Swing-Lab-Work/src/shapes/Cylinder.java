package shapes;

import exceptions.VolumeException;

public class Cylinder extends RotationFigure {
    private double height;

    public double getHeight() {
        return height;
    }

    public Cylinder(double radius, double height) {
        super(3.14*radius*radius*height, 3.14*radius*radius, radius, "Cylinder");
        if (radius < 0) {
            throw new VolumeException("Can't create figure with negative volume", 1);
        }
        if (height < 0) {
            throw new VolumeException("Can't create figure with negative volume", 2);
        }
        this.height = height;
    }
}
