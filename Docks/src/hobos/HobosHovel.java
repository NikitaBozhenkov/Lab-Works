package hobos;

import sandwich.IngredientsHeap;

public class HobosHovel {
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
            waitingHobos = 0;
            cookersCount = 0;
            sandwichCount -= 8;
            notifyAll();
            System.out.println("Sandwiches count after eating: " + sandwichCount);
        } else {
            wait();
        }
    }

    synchronized void addSandwich(String hoboName) {
        if(ingredientsHeap.isIngredientsEnoughForSandwich()) {
            ingredientsHeap.takeSandwich();
            ++sandwichCount;
            System.out.println("Hobo" + hoboName + " cooked a sandwich");
            System.out.println("Bread: " + ingredientsHeap.getBreadCount() +
                    " Sausages: " + ingredientsHeap.getSausageCount() +
                    " Mayonnaise: " + ingredientsHeap.getMayonnaiseCount() +
                    " Sandwiches count: " + sandwichCount);
        }
    }

    IngredientsHeap getIngredientsHeap() {
        return ingredientsHeap;
    }

    synchronized void addCooker() {
        ++cookersCount;
    }

    synchronized boolean hasEnoughFood() {
        return sandwichCount >= 8;
    }

    synchronized boolean hasEnoughCookers() {
        return cookersCount == 2;
    }
}
