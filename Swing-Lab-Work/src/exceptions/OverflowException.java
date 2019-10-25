package exceptions;

public class OverflowException extends RuntimeException {
    private double volume;

    public double getVolume() {
        return volume;
    }

    public OverflowException(double volume, String message) {
        super(message);
        this.volume = volume;
    }
}
