package figure;

public abstract class Figure {
    private String className;

    public Figure(String className) {
        this.className = className;
    }

    protected String getClassName() {
        return className;
    }

    public abstract String toString();
}
