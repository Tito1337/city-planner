package CityPlanner.Model;

/**
 * Représente une ville où on propose des activités
 */
public class City {
    private int id;
    private String name;

    /**
     * Constructeur
     * @param id    identifiant de la ville dans la base de données
     * @param name  nom de la ville
     */
    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return identifiant unique de la ville dans la base de données
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return nom de la ville
     */
    public String getName() {
        return this.name;
    }
}
