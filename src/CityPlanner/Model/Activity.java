package CityPlanner.Model;

import java.util.ArrayList;

/**
 * Activity représente une activité
 */
public class Activity {
    private int id;
    private City city;
    private String name;
    private String description;
    private float price;
    private float duration;
    private String address;
    private String open;
    private ArrayList<Tag> tags;
    private Boolean selected;

    /**
     * Constructeur
     * @param id    identifiant unique de l'activité dans la base de données
     * @param city  ville où se déroule l'activité
     * @param name  nom de l'activité
     * @param description   description de l'activité
     * @param price prix de l'activité, en euros
     * @param duration  durée de l'activité, en heures
     * @param address   adresse de l'activité
     * @param open  jours d'ouverture de l'activité
     * @param tags  liste de tags de l'activité
     */
    public Activity(int id, City city, String name, String description, float price, float duration, String address, String open, ArrayList<Tag> tags) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.address = address;
        this.open = open;
        this.tags = tags;
        this.selected = new Boolean(true);
    }

    /**
     * @return identifiant unique de l'activité dans la base de données
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return ville de l'activité
     */
    public City getCity() {
        return city;
    }

    /**
     * @return nom de l'activité
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return description de l'activité
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return liste de tags de l'activité
     */
    public ArrayList<Tag> getTags() {
        return tags;
    }

    /**
     * @return prix de l'activité, en euros
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return durée de l'activité, en heures
     */
    public float getDuration() {
        return duration;
    }

    /**
     * @return adresse de l'activité
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return jours d'ouverture de l'activité
     */
    public String getOpen() {
        return open;
    }

    /**
     * @return si l'activité est marquée sélectionnée (vrai par défaut)
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * @param selected booléen marquant l'activité sélectionnée ou non
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
