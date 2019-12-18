package harbor;

import ships.Ship;

public class Stock {
    private volatile int goodsUnits;

    public Stock() {
        goodsUnits = 0;
    }

    public synchronized int getGoodsUnits() {
        return goodsUnits;
    }

    public synchronized boolean isEmpty() {
        return goodsUnits == 0;
    }

    public synchronized void operateStealing() throws InterruptedException {
        Thread.sleep(3000);
        --goodsUnits;
    }

    public synchronized void operateUnloading(Ship ship) throws InterruptedException {
        while (ship.getCargoWeight() != 0) {
            goodsUnits += 5;
            ship.cargoUnload();
            Thread.sleep(1000);
        }
    }
}
