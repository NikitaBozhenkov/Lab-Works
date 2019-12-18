package sandwich;

public class IngredientsHeap {
    private volatile int mayonnaiseCount;
    private volatile int breadCount;
    private volatile int sausageCount;

    public IngredientsHeap() {
        mayonnaiseCount = 0;
        breadCount = 0;
        sausageCount = 0;
    }

    public synchronized int getBreadCount() {
        return breadCount;
    }

    public synchronized int getMayonnaiseCount() {
        return mayonnaiseCount;
    }

    public synchronized int getSausageCount() {
        return sausageCount;
    }

    public synchronized boolean canMakeSandwich() {
        return mayonnaiseCount >= 1 && breadCount >= 1 && sausageCount >= 1;
    }

    public synchronized void addIngredient(Ingredient ingredient) {
        switch (ingredient) {
            case BREAD: {
                ++breadCount;
                break;
            }
            case SAUSAGE: {
                ++sausageCount;
                break;
            }
            case MAYONNAISE: {
                ++mayonnaiseCount;
                break;
            }
            default: {
                throw new RuntimeException("Undefined ingredient type");
            }
        }
    }

    //Guaranteed that field will not become negative
    public synchronized void takeSandwich() {
        --mayonnaiseCount;
        --breadCount;
        --sausageCount;
    }
}
