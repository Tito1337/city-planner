package CityPlanner;

import CityPlanner.Model.*;
import java.sql.*;
import java.util.ArrayList;

import CityPlanner.Views.*;

public class Main {
    public static void main(String[] args) {
        try {
            Trip trip = new Trip();
            Window Citytrip = new Window(trip);
            new Thread(new Webserver(9888, trip )).start();
        } catch (Exception e) {
            System.err.printf("ERREUR JAVA : %s\n", e);
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
