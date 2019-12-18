package harbor;

import narrowTunnel.NarrowTunnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandwich.Ingredient;
import ships.Ship;

public class Dock extends Thread {
    private final Logger logger = LoggerFactory.getLogger(Dock.class);
    private final Stock stock;
    private final Ingredient ingredient;
    private Ship ship;
    private NarrowTunnel tunnel;

    public Dock(Ingredient ingredient, Stock stock, NarrowTunnel tunnel) {
        this.ingredient = ingredient;
        this.stock = stock;
        this.tunnel = tunnel;
    }

    public synchronized Stock getStock() {
        return stock;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    private void shipCall() throws InterruptedException {
        ship = tunnel.getShipOut(ingredient);
    }

    @Override
    public void run() {
        while (true) {
            //trying to get a ship
            try {
                shipCall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (ship != null) {
                logger.info(ingredient + " ship arrived to the dock");
                try {
                    stock.operateUnloading(ship);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info(ingredient + " stock finished the unloading. It now has " + stock.getGoodsUnits() + " units");

                //Workers are tired
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
