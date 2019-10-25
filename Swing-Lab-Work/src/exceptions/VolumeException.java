package exceptions;

public class VolumeException extends RuntimeException {
    private int parameterNumber;

    public int getParameterNumber() {
        return parameterNumber;
    }

    public VolumeException(String message, int parameterNumber) {
        super(message);
        this.parameterNumber = parameterNumber;
    }
}
