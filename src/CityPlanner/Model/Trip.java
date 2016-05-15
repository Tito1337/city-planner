package CityPlanner.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by tito on 5/02/16.
 */
public class Trip {
    private City city = null;
    private int personNumber;
    private Tag tag = null;
    private ArrayList<Activity> activities;
    private String startDate;
    private String endDate;

    public Trip() {

    }

    public Trip(City city, int personNumber, Tag tag) throws SQLException {
        this.city = city;
        this.personNumber = personNumber;
        this.tag = tag;

        this.activities = Database.getCityActivitiesByTag(city, tag);
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

    public void setCity(City city) throws SQLException {
        this.city = city;
        if(tag != null) {
            this.activities = Database.getCityActivitiesByTag(city, tag);
        }
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public void setTag(Tag tag) throws SQLException {
        this.tag = tag;
        if(city != null) {
            this.activities = Database.getCityActivitiesByTag(city, tag);
        }
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
