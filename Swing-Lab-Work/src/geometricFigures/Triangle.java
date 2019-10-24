package geometricFigures;

public class Triangle extends PlaneFigure {
    public Triangle(double side, double height) {
        super(side, height, side*height/2, "Triangle");
    }
}
