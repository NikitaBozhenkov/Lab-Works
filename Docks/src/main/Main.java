package main;

import harbor.Dock;
import harbor.Harbor;
import harbor.Stock;
import narrowTunnel.NarrowTunnel;
import sandwich.Ingredient;
import shipsGenerator.ShipsGenerator;

public class Main {
    public static void main(String[] args) {
        NarrowTunnel tunnel = new NarrowTunnel();
        ShipsGenerator shipsGenerator = new ShipsGenerator(tunnel);
        Harbor harbor = new Harbor(new Dock(Ingredient.BREAD, new Stock(), tunnel),
                new Dock(Ingredient.MAYONNAISE, new Stock(), tunnel),
                new Dock(Ingredient.SAUSAGE, new Stock(), tunnel));
        shipsGenerator.start();
        harbor.startWorkingDay();
    }
}
