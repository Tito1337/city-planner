package CityPlanner;

import CityPlanner.Model.*;
import CityPlanner.Views.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Instance de Trip partagée entre la GUI et le Web
            Trip trip = new Trip();

            Window Citytrip = new Window(trip); // GUI
            new Thread(new Webserver(9888, trip)).start(); // Web
        } catch (Exception e) {
            System.err.printf("ERREUR JAVA : %s\n", e);
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
