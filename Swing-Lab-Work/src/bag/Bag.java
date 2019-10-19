package bag;

import exceptions.NegativeVolumeException;
import exceptions.OverflowException;
import shapes.Shape;

import java.util.ArrayList;

public class Bag {
    private double volume;
    private ArrayList<Shape> bag;

    public Bag(double volume) {
        this.volume = volume;
        bag = new ArrayList<Shape>(1);
    }

    public void addFigure(Shape figure) throws OverflowException {
        if (volume >= figure.getVolume()) {
            bag.add(figure);
            volume -= figure.getVolume();
        } else {
            throw new OverflowException(bag.size(), "Can't put the figure");
        }
        bag.sort(Shape::compareTo);
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        if (volume < 0) {
            throw new NegativeVolumeException(volume, "Can't create bag with negative volume.");
        }
        this.volume = volume;
    }

    public Shape getElement(int index) {
        return bag.get(index);
    }

    public int getSize() {
        return bag.size();
    }

    public void deleteShape(Shape shape) {
        bag.remove(shape);
    }

    public void clear() {
        bag.clear();
    }

    public void printVolumes() {
        for (Shape shape : bag) {
            System.out.print(shape.getVolume() + " ");
        }
    }

    public String toString() {
        String output = "";
        for (Shape shape : bag) {
            output += "Shape - " + shape.getClass().getName() + ", " + "Volume - " + shape.getVolume() + "\n";
        }
        return output;
    }
}


