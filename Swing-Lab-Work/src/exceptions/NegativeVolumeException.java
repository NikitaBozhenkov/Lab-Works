package exceptions;

public class NegativeVolumeException extends RuntimeException {
    public NegativeVolumeException(String message) {
        super(message);
    }
}
