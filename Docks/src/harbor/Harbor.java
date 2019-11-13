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

    public Dock getBreadDock() {
        return breadDock;
    }

    public Dock getMayonnaiseDock() {
        return mayonnaiseDock;
    }

    public Dock getSausageDock() {
        return sausageDock;
    }
}
