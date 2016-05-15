package CityPlanner.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by tito on 5/02/16.
 */
public class Trip {
    private City city;
    private int personNumber;
    private Tag tag;
    private ArrayList<Activity> activities;

    public Trip(City city, int personNumber, Tag tag) throws SQLException {
        this.city = city;
        this.personNumber = personNumber;
        this.tag = tag;

        this.activities = Database.getCityActivities(city);
    }

    public City getCity() {
        return city;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public Tag getTag() {
        return tag;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }
}
