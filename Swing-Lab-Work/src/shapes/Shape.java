package shapes;

public abstract class Shape implements Comparable<Shape> {
    private double volume;

    public Shape(double volume) {
        this.volume = volume;
    }

    @Override
    public int compareTo(Shape o) {
        return Double.compare(o.getVolume(), this.getVolume());
    }

    public double getVolume() {
        return volume;
    }
};

