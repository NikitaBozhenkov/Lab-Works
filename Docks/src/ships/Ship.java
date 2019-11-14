package ships;

import sandwich.Ingredient;

public class Ship {
    private int cargoWeight;
    private Ingredient cargoType;

    public Ship(int cargoWeight, Ingredient cargoType) {
        this.cargoWeight = cargoWeight;
        this.cargoType = cargoType;
    }

    public Ingredient getCargoType() {
        return cargoType;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void cargoUnload() {
        cargoWeight -= 5;
    }
}
