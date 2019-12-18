package shipsGenerator;

import narrowTunnel.NarrowTunnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandwich.Ingredient;
import ships.Ship;

import java.util.Random;

public class ShipsGenerator extends Thread {
    private final Logger logger = LoggerFactory.getLogger(ShipsGenerator.class);
    private NarrowTunnel tunnel;

    public ShipsGenerator(NarrowTunnel tunnel) {
        this.tunnel = tunnel;
    }

    @Override
    public void run() {
        Random random = new Random();
        Ship ship;
        int weight;
        Ingredient ingredient;
        int shipsCount = 0;

        while (true) {
            // Creating a ship
            double weightRandomValue = random.nextDouble();
            double ingredientRandomValue = random.nextDouble();

            if (weightRandomValue < 0.33) {
                weight = 10;
            } else if (weightRandomValue < 0.66) {
                weight = 20;
            } else {
                weight = 30;
            }
            if (ingredientRandomValue < 0.33) {
                ingredient = Ingredient.SAUSAGE;
            } else if (ingredientRandomValue < 0.66) {
                ingredient = Ingredient.BREAD;
            } else {
                ingredient = Ingredient.MAYONNAISE;
            }

            ship = new Ship(weight, ingredient);
            ++shipsCount;

            if (tunnel.getShipIn(ship)) {
                logger.info(" ship №" + shipsCount + " (" + weight + " units of " + ingredient + ") arrived to tunnel");
            } else {
                logger.warn(" ship №" + shipsCount + " (" + weight + " units of " + ingredient + ") sank");
            }

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
