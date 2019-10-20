package shapes;

public class Cylinder extends RotationFigure {
    private double height;

    public double getHeight() {
        return height;
    }

    public Cylinder(double radius, double height) {
        super(3.14*radius*radius*height, 3.14*radius*radius, radius, "Cylinder");
        this.height = height;
    }
}
