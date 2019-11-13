package harbor;

public class Stock {
    public int goodUnits;
    private volatile boolean isOperated;

    public Stock() {
        goodUnits = 0;
        isOperated = false;
    }

    public void setOperatedFlag(boolean flag) {
        isOperated = flag;
    }

    public boolean isOperated() {
        return isOperated;
    }

    public boolean isEmpty() {
        return goodUnits == 0;
    }
}
