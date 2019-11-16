package hobos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandwich.IngredientsHeap;

public class HobosHovel {
    private final Logger logger = LoggerFactory.getLogger(HobosHovel.class);
    private IngredientsHeap ingredientsHeap;
    private volatile int sandwichCount;
    private volatile int cookersCount;
    private volatile int waitingHobos;

    public HobosHovel(IngredientsHeap ingredientsHeap) {
        this.ingredientsHeap = ingredientsHeap;
        sandwichCount = 0;
        cookersCount = 0;
    }

    synchronized void updateStatus() throws InterruptedException {
        ++waitingHobos;
        if(waitingHobos == 8) {
            logger.info("SPICY MEAT. HE-HE BOOOOOOY");
            waitingHobos = 0;
            cookersCount = 0;
            sandwichCount -= 8;
            notifyAll();
        } else {
            wait();
        }
    }

    public synchronized boolean addSandwich() {
        if(ingredientsHeap.isIngredientsEnoughForSandwich()) {
            ingredientsHeap.takeSandwich();
            logger.info("hovel state: Bread - " + ingredientsHeap.getBreadCount() +
                    " Mayonnaise - " + ingredientsHeap.getMayonnaiseCount() +
                    " Sausages - " + ingredientsHeap.getSausageCount());
            ++sandwichCount;
            return true;
        }
        return false;
    }

    public int getSandwichCount() {
        return sandwichCount;
    }

    public IngredientsHeap getIngredientsHeap() {
        return ingredientsHeap;
    }

    public synchronized void addCooker() {
        ++cookersCount;
    }

    public synchronized boolean hasEnoughFood() {
        return sandwichCount >= 8;
    }

    public synchronized boolean canAddCooker() {
        return cookersCount < 2;
    }

    public synchronized boolean tryAddCooker() {
        if (!canAddCooker()) {
            return false;
        }
        addCooker();
        return true;
    }
}
