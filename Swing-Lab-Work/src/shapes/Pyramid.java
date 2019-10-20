package shapes;

import exceptions.NegativeVolumeException;

public class Pyramid extends ConvexFigure {
    private double height;

    public double getHeight() {
        return height;
    }

    public Pyramid(double side, double baseHeight, double height) {
        super(side*baseHeight*height/6, side*baseHeight/2, side, "Pyramid");
        if (side < 0 || baseHeight < 0 || height < 0) if (side < 0) throw new NegativeVolumeException("Can't create figure with negative volume");
        this.height = height;
    }
}
