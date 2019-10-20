package shapes;

import figure.Figure;

public abstract class Shape extends Figure implements Comparable<Shape> {
    private double volume;

    public Shape(double volume, String className) {
        super(className);
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

