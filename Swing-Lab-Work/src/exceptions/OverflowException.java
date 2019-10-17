package exceptions;

public class OverflowException extends RuntimeException {
    private int size;

    public int getSize() {
        return size;
    }

    public OverflowException(int size, String message) {
        super(message);
        this.size = size;
    }
}
