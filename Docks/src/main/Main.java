package main;

import harbor.Dock;
import harbor.Harbor;
import harbor.Stock;
import hobos.Hobo;
import hobos.HobosHovel;
import narrowTunnel.NarrowTunnel;
import sandwich.Ingredient;
import sandwich.IngredientsHeap;
import shipsGenerator.ShipsGenerator;

public class Main {
    public static void main(String[] args) {
        NarrowTunnel tunnel = new NarrowTunnel();
        ShipsGenerator shipsGenerator = new ShipsGenerator(tunnel);
        Harbor harbor = new Harbor(new Dock(Ingredient.BREAD, new Stock(), tunnel),
                new Dock(Ingredient.MAYONNAISE, new Stock(), tunnel),
                new Dock(Ingredient.SAUSAGE, new Stock(), tunnel));
        HobosHovel hobosHovel = new HobosHovel(new IngredientsHeap());
        Hobo[] hobos = new Hobo[]{new Hobo(harbor, "#1", hobosHovel),
                new Hobo(harbor, "#2", hobosHovel), new Hobo(harbor, "#3", hobosHovel),
                new Hobo(harbor, "#4", hobosHovel), new Hobo(harbor, "#5", hobosHovel),
                new Hobo(harbor, "#6", hobosHovel), new Hobo(harbor, "#7", hobosHovel),
                new Hobo(harbor, "#8", hobosHovel)};

        for(Hobo hobo : hobos) {
            hobo.start();
        }
        shipsGenerator.start();
        harbor.startWorkingDay();
    }
}
