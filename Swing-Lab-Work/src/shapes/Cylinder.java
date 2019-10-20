package shapes;

import exceptions.NegativeVolumeException;

public class Cylinder extends RotationFigure {
    private double height;

    public double getHeight() {
        return height;
    }

    public Cylinder(double radius, double height) {
        super(3.14*radius*radius*height, 3.14*radius*radius, radius, "Cylinder");
        if (radius < 0 || height < 0) throw new NegativeVolumeException("Can't create figure with negative volume");
        this.height = height;
    }
}
