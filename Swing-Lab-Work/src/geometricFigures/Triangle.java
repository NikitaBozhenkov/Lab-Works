package geometricFigures;

public class Triangle extends GeometricFigure {
    public Triangle(double side, double height) {
        super(side, height, side*height/2, "Triangle");
    }
}
