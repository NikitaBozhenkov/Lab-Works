package shipsGenerator;

import narrowTunnel.NarrowTunnel;
import sandwich.Ingredient;
import ships.Ship;

import java.util.Random;

public class ShipsGenerator extends Thread {
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

        while(true) {
            double weightRandomValue = random.nextDouble();
            double ingredientRandomValue = random.nextDouble();

            if(weightRandomValue < 0.33) {
                weight = 10;
            } else if (weightRandomValue < 0.66) {
                weight = 20;
            } else {
                weight = 30;
            }
            if(ingredientRandomValue < 0.33) {
                ingredient = Ingredient.SAUSAGE;
            } else if (ingredientRandomValue < 0.66) {
                ingredient = Ingredient.BREAD;
            } else {
                ingredient = Ingredient.MAYONNAISE;
            }

            ship = new Ship(weight,ingredient);
            tunnel.getShipIn(ship);
            ++shipsCount;
            System.out.println("Generated ship number " + shipsCount);

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
