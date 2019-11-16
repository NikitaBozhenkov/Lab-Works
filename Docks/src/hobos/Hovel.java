package hobos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandwich.IngredientsHeap;

public class Hovel {
    private final Logger logger = LoggerFactory.getLogger(Hovel.class);
    private final IngredientsHeap ingredientsHeap;
    private volatile int sandwichCount;
    private volatile int cookersCount;
    private volatile int waitingHobos;

    public Hovel(IngredientsHeap ingredientsHeap) {
        this.ingredientsHeap = ingredientsHeap;
        this.sandwichCount = 0;
        this.cookersCount = 0;
    }

    public int getSandwichCount() {
        return sandwichCount;
    }

    public IngredientsHeap getIngredientsHeap() {
        return ingredientsHeap;
    }

    public synchronized boolean canAddCooker() {
        return cookersCount < 2;
    }

    public synchronized boolean tryAddCooker() {
        if (!canAddCooker()) {
            return false;
        }
        ++cookersCount;
        return true;
    }

    public synchronized boolean hasEnoughFood() {
        return sandwichCount >= 8;
    }

    public synchronized boolean addSandwich() {
        if (ingredientsHeap.canMakeSandwich()) {
            ingredientsHeap.takeSandwich();
            logger.info("hovel state: Bread - " + ingredientsHeap.getBreadCount() +
                    " Mayonnaise - " + ingredientsHeap.getMayonnaiseCount() +
                    " Sausages - " + ingredientsHeap.getSausageCount());
            ++sandwichCount;
            return true;
        }
        return false;
    }

    public synchronized void updateStatus() throws InterruptedException {
        ++waitingHobos;
        if (waitingHobos == 8) {
            logger.info("Nice and hot. Hot and spicy meat. He-he BOOOI");
            waitingHobos = 0;
            cookersCount = 0;
            sandwichCount -= 8;
            notifyAll();
        } else {
            wait();
        }
    }
}
