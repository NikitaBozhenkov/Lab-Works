package hobos;

import harbor.Dock;
import harbor.Harbor;

import java.util.Random;

public class Hobo extends Thread {
    public enum Profession {
        sundowner,
        thief,
        cooker
    }

    private final Harbor harbor;
    private String hoboName;
    private Profession profession;
    private final HobosHovel hovel;
    private Dock dock;

    public Hobo(Harbor harbor, String hoboName, HobosHovel hovel) {
        this.harbor = harbor;
        this.hoboName = hoboName;
        profession = Profession.sundowner;
        this.hovel = hovel;
    }

    private void professionChoose() {
        if (hovel.hasEnoughCookers()) {
            this.profession = Profession.thief;
        } else {
            this.profession = Profession.cooker;
            hovel.addCooker();
        }
        System.out.println("Hobo " + hoboName + " became a " + profession);
    }

    public String getHoboName() {
        return hoboName;
    }

    private void dockChoose() {
        Random random = new Random();
        double stockRandomValue = random.nextDouble();

        if (stockRandomValue < 0.33) {
            dock = harbor.getBreadDock();
        } else if (stockRandomValue < 0.66) {
            dock = harbor.getMayonnaiseDock();
        } else {
            dock = harbor.getSausageDock();
        }
    }

    @Override
    public void run() {

        while (true) {

            if (!hovel.hasEnoughFood()) {
                switch (profession) {
                    case sundowner: {
                        professionChoose();
                        break;
                    }
                    case thief: {
                        //Choosing dock
                        dockChoose();

                        if (!dock.getStock().isEmpty()) {
                            try {
                                dock.getStock().operateStealing();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            hovel.getIngredientsHeap().addIngredient(dock.getIngredient());
                            System.out.println("Hobo " + hoboName + " bring " + dock.getIngredient());
                            break;
                        }
                    }
                    case cooker: {
                        hovel.addSandwich(hoboName);
                        break;
                    }
                    default: {
                        throw new RuntimeException("Undefined profession");
                    }
                }
            } else {
                profession = Profession.sundowner;
                try {
                    hovel.updateStatus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
