package narrowTunnel;

import sandwich.Ingredient;
import ships.Ship;

import java.util.ArrayList;

public class NarrowTunnel {
    private ArrayList<Ship> tunnel;
    private int shipsIn;

    public NarrowTunnel() {
        tunnel = new ArrayList<>(5);
        shipsIn = 0;
    }

    private boolean isOpened() {
        return shipsIn != 5;
    }

    public void getShipIn(Ship ship) {
        if (isOpened()) {
            tunnel.add(ship);
        }
    }

    public Ship getShipOut(Ingredient ingredient) {
        for (Ship ship : tunnel) {
            if (ship.getCargoType() == ingredient) {
                return ship;
            }
        }
        return null;
    }


}
