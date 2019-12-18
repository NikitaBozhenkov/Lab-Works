package hobos;

import harbor.Dock;
import harbor.Harbor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Hobo extends Thread {
    public enum Profession {
        sundowner,
        thief,
        cooker
    }

    private final Logger logger = LoggerFactory.getLogger(Hobo.class);
    private final Harbor harbor;
    private final String hoboName;
    private Profession profession;
    private final Hovel hovel;
    private Dock dock;

    public Hobo(Harbor harbor, String hoboName, Hovel hovel) {
        this.harbor = harbor;
        this.hoboName = hoboName;
        this.profession = Profession.sundowner;
        this.hovel = hovel;
    }

    private void professionChoose() {
        if (hovel.tryAddCooker()) {
            this.profession = Profession.cooker;
        } else {
            this.profession = Profession.thief;
        }
        logger.info(hoboName + " became a " + profession);
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

                        // trying to steal
                        if (!dock.getStock().isEmpty()) {
                            try {
                                dock.getStock().operateStealing();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            hovel.getIngredientsHeap().addIngredient(dock.getIngredient());
                            logger.info(hoboName + " bring " + dock.getIngredient());
                            break;
                        }
                    }
                    case cooker: {
                        if (hovel.addSandwich()) {
                            logger.info(hoboName + " cooked a sandwich. Hobos now have "
                                    + hovel.getSandwichCount() + " sandwiches");
                        }
                        break;
                    }
                    default: {
                        throw new RuntimeException("Undefined profession");
                    }
                }
            } else {
                //reset a profession
                profession = Profession.sundowner;

                // decide: wait or notify
                try {
                    hovel.updateStatus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Sleep after getting meals
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
