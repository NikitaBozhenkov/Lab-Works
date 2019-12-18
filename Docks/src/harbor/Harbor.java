package harbor;

public class Harbor {
    private Dock breadDock;
    private Dock mayonnaiseDock;
    private Dock sausageDock;

    public Harbor(Dock breadDock, Dock mayonnaiseDock, Dock sausageDock) {
        this.breadDock = breadDock;
        this.mayonnaiseDock = mayonnaiseDock;
        this.sausageDock = sausageDock;
    }

    public synchronized Dock getBreadDock() {
        return breadDock;
    }

    public synchronized Dock getMayonnaiseDock() {
        return mayonnaiseDock;
    }

    public synchronized Dock getSausageDock() {
        return sausageDock;
    }

    public void startWorkingDay() {
        breadDock.start();
        mayonnaiseDock.start();
        sausageDock.start();
    }
}
