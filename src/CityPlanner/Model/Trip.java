package CityPlanner.Model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe représentant un voyage
 */
public class Trip {
    private City city = null;
    private int personNumber;
    private Tag tag = null;
    private ArrayList<Activity> activities;
    private String startDate;
    private String endDate;

    /**
     * Constructeur vide pour instanciation partagée
     */
    public Trip() {
    }

    /**
     * Constructeur classique
     * @param city ville où se déroule le voyage
     * @param personNumber nombre de participants
     * @param tag tag d'activités à rechercher
     * @throws SQLException
     */
    public Trip(City city, int personNumber, Tag tag) throws SQLException {
        this.city = city;
        this.personNumber = personNumber;
        this.tag = tag;

        this.activities = Database.getCityActivitiesByTag(city, tag);
    }

    /**
     * @return ville où se déroule le voyage
     */
    public City getCity() {
        return city;
    }

    /**
     * @return nombre de participants
     */
    public int getPersonNumber() {
        return personNumber;
    }

    /**
     * @return tag d'activités à rechercher
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @return liste des activités
     */
    public ArrayList<Activity> getActivities() {
        return activities;
    }

    /**
     * Indiquer ou modifier la ville du voyage. Met à jour la liste des activités.
     * @param city ville où se déroule le voyage
     * @throws SQLException
     */
    public void setCity(City city) throws SQLException {
        this.city = city;
        if(tag != null) {
            this.activities = Database.getCityActivitiesByTag(city, tag);
        }
    }

    /**
     * Indiquer ou modifier le nombre de participants
     * @param personNumber nombre de participants
     */
    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    /**
     * Indiquer ou modifier le tag des activités recherchées. Met à jour la liste des activités.
     * @param tag tag d'activités à rechercher
     * @throws SQLException
     */
    public void setTag(Tag tag) throws SQLException {
        this.tag = tag;
        if(city != null) {
            this.activities = Database.getCityActivitiesByTag(city, tag);
        }
    }

    /**
     * @return date de début du voyage
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Indiquer ou modifier la date de début du voyage
     * @param startDate date de début du voyage
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return date de fin du voyage
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Indiquer ou modifier la date de fin du voyage
     * @param endDate date de fin du voyage
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
