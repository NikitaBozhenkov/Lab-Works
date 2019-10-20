package shapes;

public class Pyramid extends ConvexFigure {
    private double height;

    public double getHeight() {
        return height;
    }

    public Pyramid(double side, double baseHeight, double height) {
        super(side*baseHeight*height/6, side*baseHeight/2, side, "Pyramid");
        this.height = height;
    }
}
