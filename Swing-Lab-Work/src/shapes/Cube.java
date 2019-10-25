package shapes;

import exceptions.VolumeException;

public class Cube extends ConvexFigure {
    @Override
    public String getBaseFigure() {
        return "Square";
    }

    public Cube(double side) {
        super(side*side*side, side*side, side, "Cube");
        if (side < 0) {
            throw new VolumeException("Can't create figure with negative volume", 1);
        }
    }
}
