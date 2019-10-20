package shapes;

import exceptions.VolumeException;
import figure.Figure;

public abstract class Shape extends Figure implements Comparable<Shape> {
    private double volume;

    Shape(double volume, String className) {
        super(className);
        if(volume < 0) throw new VolumeException("Can't create figure with negative volume");
        this.volume = volume;
    }

    @Override
    public String toString() {
        return getClassName() + ", volume: " + getVolume();
    }

    @Override
    public int compareTo(Shape o) {
        return Double.compare(o.getVolume(), this.getVolume());
    }

    public double getVolume() {
        return volume;
    }
};

