package bag;

import exceptions.OverflowException;
import exceptions.VolumeException;
import shapes.Shape;

import java.util.ArrayList;
import java.util.Collections;

public class Bag {
    private double freeVolume;
    private double capacity;
    private ArrayList<Shape> bag;

    public Bag(double capacity) {
        if (capacity < 0) {
            throw new VolumeException("Can't create bag with negative volume.");
        }
        this.freeVolume = capacity;
        this.capacity = capacity;
        bag = new ArrayList<Shape>();
    }

    public void addFigure(Shape figure) throws OverflowException {
            if (freeVolume >= figure.getVolume()) {
                bag.add(figure);
                freeVolume -= figure.getVolume();
            } else {
                throw new OverflowException(freeVolume, "Can't put the figure");
            }
        Collections.sort(bag);
    }

    public double getFreeVolume() {
        return freeVolume;
    }

    public double getCapacity() {
        return capacity;
    }

    public Shape getElement(int index) {
        return bag.get(index);
    }

    public int getSize() {
        return bag.size();
    }

    public void deleteShape(Shape shape) {
        bag.remove(shape);
        freeVolume += shape.getVolume();
    }

    public void clear() {
        bag.clear();
        freeVolume = capacity;
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


