package shapes;

import exceptions.VolumeException;

public class Pyramid extends ConvexFigure {
    private double height;

    @Override
    public String getBaseFigure() {
        return "Triangle";
    }

    public double getHeight() {
        return height;
    }

    public Pyramid(double side, double baseHeight, double height) {
        super(side*baseHeight*height/6, side*baseHeight/2, side, "Pyramid");
        if (side < 0) {
            throw new VolumeException("Can't create figure with negative volume",1);
        }
        if (baseHeight < 0) {
            throw new VolumeException("Can't create figure with negative volume",2);
        }
        if (height < 0) {
            throw new VolumeException("Can't create figure with negative volume",3);
        }
        this.height = height;
    }
}
