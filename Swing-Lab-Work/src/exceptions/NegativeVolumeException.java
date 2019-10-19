package exceptions;

public class NegativeVolumeException extends RuntimeException {
    private double volume;

    public double getVolume() {
        return volume;
    }

    public NegativeVolumeException(double volume, String message) {
        super(message);
        this.volume = volume;
    }
}
