package shapes;

import exceptions.NegativeVolumeException;

public class Cube extends ConvexFigure {
    public Cube(double side) {
        super(side*side*side, side*side, side, "Cube");
        if (side < 0) throw new NegativeVolumeException("Can't create figure with negative volume");
    }
}
