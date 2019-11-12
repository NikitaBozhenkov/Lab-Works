package main;


import narrowTunnel.NarrowTunnel;
import shipsGenerator.ShipsGenerator;

public class Main {
    public static void main(String[] args) {
        NarrowTunnel tunnel = new NarrowTunnel();
        ShipsGenerator shipsGenerator = new ShipsGenerator(tunnel);
        shipsGenerator.start();
    }
}
