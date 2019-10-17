package shapes;

public class Cube extends ConvexFigure {
    public Cube(double side) {
        super(side*side*side, side*side, side);
    }
}
