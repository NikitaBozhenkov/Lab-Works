package shapes;

public class Sphere extends RotationFigure {
    public Sphere(double radius) {
        super(4*3.14*radius*radius*radius/3, 3.14*radius*radius, radius);
    }
}
