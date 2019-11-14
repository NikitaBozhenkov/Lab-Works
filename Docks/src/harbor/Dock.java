package harbor;

import narrowTunnel.NarrowTunnel;
import sandwich.Ingredient;
import ships.Ship;

public class Dock extends Thread {
    private volatile Stock stock;
    private final Ingredient ingredient;
    private Ship ship;
    private volatile NarrowTunnel tunnel;

    public Dock(Ingredient ingredient, Stock stock, NarrowTunnel tunnel) {
        this.ingredient = ingredient;
        this.stock = stock;
        this.tunnel = tunnel;
    }

    @Override
    public void run() {
        while(true) {
            //trying to get a ship
            try {
                shipCall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ship != null) {
                System.out.println(ingredient + " stock: successful call");
                // isOperated check
                while(stock.isOperated()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(ingredient + " stock start unloading the cargo ");
                stock.setOperatedFlag(true);

                //Unloading the cargo
                while (ship.getCargoWeight() != 0) {
                        ship.cargoUnload();
                        stock.operateGoods(true);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stock.setOperatedFlag(false);
            }

            System.out.println(ingredient + " stock: " + stock.getGoodUnits());

            //Sleep after the whole cycle or bad call
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void shipCall() throws InterruptedException {
        ship = tunnel.getShipOut(ingredient);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
