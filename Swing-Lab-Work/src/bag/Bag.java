package bag;

import exceptions.VolumeException;
import exceptions.OverflowException;
import shapes.Shape;

import java.util.ArrayList;

public class Bag {
    private double volume;
    private double capacity;
    private ArrayList<Shape> bag;

    public Bag(double volume) {
        this.volume = volume;
        this.capacity = volume;
        bag = new ArrayList<Shape>(1);
    }

    public void addFigure(Shape figure) throws OverflowException {
        if (figure.getVolume() >= 0.00001) {
            if (volume >= figure.getVolume()) {
                bag.add(figure);
                volume -= figure.getVolume();
            } else {
                throw new OverflowException(volume, "Can't put the figure");
            }
            bag.sort(Shape::compareTo);
        }
    }

    public double getVolume() {
        return volume;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setVolume(double volume) {
        if (volume < 0) {
            throw new VolumeException("Can't create bag with negative volume.");
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
        volume += shape.getVolume();
    }

    public void clear() {
        bag.clear();
        volume = capacity;
    }

    public void printVolumes() {
        for (Shape shape : bag) {
            System.out.print(shape.getVolume() + " ");
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Shape shape : bag) {
            output.append("Shape - ").append(shape.getClass().getName()).append(", ").append("Volume - ").append(shape.getVolume()).append("\n");
        }
        return output.toString();
    }
}


