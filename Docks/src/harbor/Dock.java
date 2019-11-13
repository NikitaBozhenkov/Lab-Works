package harbor;

import narrowTunnel.NarrowTunnel;
import sandwich.Ingredient;
import ships.Ship;

public class Dock extends Thread {
    private Stock stock;
    private Ingredient ingredient;
    private Ship ship;
    private NarrowTunnel tunnel;
    private boolean isFree;

    public void shipCall(NarrowTunnel tunnel) {

    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public boolean isFree() {
        return isFree;
    }

    private void updateStatus() {
        if (ship != null) {
            isFree = false;
        }
    }
}
