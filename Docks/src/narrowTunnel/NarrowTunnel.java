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

    public synchronized Ship getShipOut(Ingredient ingredient) {
        for (Ship ship : tunnel) {
            if (ship.getCargoType() == ingredient) {
                return ship;
            }
        }
        return null;
    }


}
