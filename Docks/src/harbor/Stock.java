package harbor;

import ships.Ship;

public class Stock {
    private volatile int goodUnits;

    public Stock() {
        goodUnits = 0;
    }

    synchronized int getGoodUnits() {
        return goodUnits;
    }

    public synchronized boolean isEmpty() {
        return goodUnits == 0;
    }

    public synchronized void operateStealing() throws InterruptedException {
        Thread.sleep(3000);
        goodUnits -= 1;
    }

    public synchronized void operateUploading(Ship ship) throws InterruptedException {
        while (ship.getCargoWeight() != 0) {
            goodUnits += 5;
            ship.cargoUnload();
            Thread.sleep(1000);
        }
    }
}
