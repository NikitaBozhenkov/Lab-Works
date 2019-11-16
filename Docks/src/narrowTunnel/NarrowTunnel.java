package narrowTunnel;

import sandwich.Ingredient;
import ships.Ship;

import java.util.ArrayList;

public class NarrowTunnel {
    private volatile ArrayList<Ship> tunnel;
    private volatile int shipsIn;

    public NarrowTunnel() {
        tunnel = new ArrayList<>(5);
        shipsIn = 0;
    }

    private boolean isOpened() {
        return shipsIn != 5;
    }

    public synchronized void getShipIn(Ship ship) {
        if (isOpened()) {
            tunnel.add(ship);
            ++shipsIn;
        }
    }

    public synchronized Ship getShipOut(Ingredient ingredient) throws InterruptedException {
        if (!tunnel.isEmpty()) {
            if (tunnel.get(shipsIn - 1).getCargoType() == ingredient) {
                Ship ship = tunnel.get(shipsIn - 1);
                tunnel.remove(shipsIn - 1);
                --shipsIn;
                notifyAll();
                return ship;
            } else {
                notifyAll();
                wait();
            }
        }
        notifyAll();
        return null;
    }
}
