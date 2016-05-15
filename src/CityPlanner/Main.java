package CityPlanner;

import CityPlanner.Model.*;
import java.sql.*;
import java.util.ArrayList;

import CityPlanner.Views.Window;

public class Main {
    public static void main(String[] args) {
        try {
            Window Citytrip = new Window();
            ArrayList<City> cities = Database.getAllCities();
            for(City city: cities) {
                System.out.printf("### %s ###\n", city.getName());
                ArrayList<Activity> activities = Database.getCityActivities(city);
                for(Activity activity: activities) {
                    System.out.printf("%s\n", activity.getName());
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.printf("ERREUR JAVA : %s\n", e);
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
