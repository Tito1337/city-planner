package CityPlanner.Model;

/**
 * Représente un tag décrivant un type ou public cicle d'une activité
 */
public class Tag {
    private int id;
    private String name;

    /**
     * Constructeur
     * @param id    identifiant du tag dans la base de données
     * @param name  nom décrivant le tag
     */
    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return identifiant du tag dans la base de données
     */
    public int getId() {
        return id;
    }

    /**
     * @return nom décrivant le tag
     */
    public String getName() {
        return name;
    }
}
