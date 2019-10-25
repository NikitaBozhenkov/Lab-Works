package figure;

public abstract class Figure {
    private String className;

    public Figure(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public abstract String getDimension();
}
