package CityPlanner.Model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tito on 5/02/16.
 */
public class Trip {
    City city;
    int personNumber;
    Tag tag;
    ArrayList<Activity> activities;

    public Trip(City city, int personNumber, Tag tag) throws SQLException {
        this.city = city;
        this.personNumber = personNumber;
        this.tag = tag;

        this.activities = Database.getCityActivities(city);
    }

    public String toString() {
        String out = "Voyage à "+this.city+"\n";
        return out;
    }
}
