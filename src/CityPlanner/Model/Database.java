package CityPlanner.Model;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection conn;

    /**
     * Méthode statique permettant de récupérer l'unique connexion à la base de données
     * @return connexion unique à la base de données SQL
     */
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:Database.db");
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return conn;
    }



    /**
     * Récupérer une ville dans la base de données
     * @param id identifiant unique de la ville
     * @return la ville ayant l'identifiant id
     * @throws SQLException
     */
    public static City getCity(int id) throws SQLException {
        Statement stmt = getConnection().createStatement();
        ResultSet query = stmt.executeQuery("SELECT * FROM city WHERE id='"+id+"'");
        if(query.next()) {
            return new City(query.getInt("id"), query.getString("name"));
        } else {
            return null;
        }
    }

    /**
     * Récupérer toutes les villes
     * @return liste de toutes les villes dans la base de données
     * @throws SQLException
     */
    public static ArrayList<City> getAllCities() throws SQLException {
        ArrayList<City> res = new ArrayList<City>();

        Statement stmt = getConnection().createStatement();
        ResultSet query = stmt.executeQuery("SELECT * FROM city");
        while(query.next()) {
            res.add(new City(query.getInt("id"), query.getString("name")));
        }

        return res;
    }



    /**
     * Récupérer toutes les activités
     * @return liste de toutes les activités
     * @throws SQLException
     */
    public static ArrayList<Activity> getAllActivities() throws SQLException {
        ArrayList<Activity> res = new ArrayList<Activity>();

        Statement stmt = getConnection().createStatement();
        ResultSet query = stmt.executeQuery("SELECT * FROM activity");
        while(query.next()) {
            res.add(new Activity(query.getInt("id"), getCity(query.getInt("city")), query.getString("name"),
                    query.getString("description"),  query.getFloat("price"), query.getFloat("duration"),
                    query.getString("address"), query.getString("open"), getActivityTags(query.getInt("id"))));
        }

        return res;
    }

    /**
     * Récupérer les activités d'une certaine ville
     * @param city ville où se déroulent les activités
     * @return liste des activités de la ville city
     * @throws SQLException
     */
    public static ArrayList<Activity> getCityActivities(City city) throws SQLException {
        ArrayList<Activity> res = new ArrayList<Activity>();

        Statement stmt = getConnection().createStatement();
        ResultSet query = stmt.executeQuery("SELECT * FROM activity WHERE city='"+city.getId()+"'");
        while(query.next()) {
            res.add(new Activity(query.getInt("id"), city, query.getString("name"), query.getString("description"),
                                 query.getFloat("price"), query.getFloat("duration"), query.getString("address"),
                                 query.getString("open"), getActivityTags(query.getInt("id"))));
        }

        return res;
    }

    /**
     * Récupérer les activités d'une certaine ville, filtré par tag
     * @param city ville où se déroulent les activités
     * @param tag tag dont doivent faire partie les activités
     * @return liste des activités de la ville city
     * @throws SQLException
     */
    public static ArrayList<Activity> getCityActivitiesByTag(City city, Tag tag) throws SQLException {
        ArrayList<Activity> res = new ArrayList<Activity>();

        Statement stmt = getConnection().createStatement();
        ResultSet query = stmt.executeQuery("SELECT * FROM activity, activity_to_tag WHERE activity_to_tag.activity=activity.id AND city='"+city.getId()+"' AND tag='"+tag.getId()+"'");
        while(query.next()) {
            res.add(new Activity(query.getInt("id"), city, query.getString("name"), query.getString("description"),
                    query.getFloat("price"), query.getFloat("duration"), query.getString("address"),
                    query.getString("open"), getActivityTags(query.getInt("id"))));
        }

        return res;
    }


    /**
     * Récupérer un tag dans la base de données
     * @param id identifiant unique du tag
     * @return le tag ayant l'identifiant id
     * @throws SQLException
     */
    public static Tag getTag(int id) throws SQLException {
        Statement stmt = getConnection().createStatement();
        ResultSet query = stmt.executeQuery("SELECT * FROM tag WHERE id='"+id+"'");
        if(query.next()) {
            return new Tag(query.getInt("id"), query.getString("name"));
        } else {
            return null;
        }
    }

    /**
     * Récupérer tous les tags
     * @return liste de tous les tags
     * @throws SQLException
     */
    public static ArrayList<Tag> getAllTags() throws SQLException {
        ArrayList<Tag> res = new ArrayList<Tag>();

        Statement stmt = getConnection().createStatement();
        ResultSet query = stmt.executeQuery("SELECT * FROM tag");
        while (query.next()) {
            res.add(new Tag(query.getInt("id"), query.getString("name")));
        }

        return res;
    }

    /**
     * Récupérer les tags d'une certaine activité (par identifiant unique)
     * @param activity_id identifiant de l'activité dont on veut les tags
     * @return liste des tags de l'activité ayant l'identifiant activity_id
     * @throws SQLException
     */
    public static ArrayList<Tag> getActivityTags(int activity_id) throws SQLException {
        ArrayList<Tag> res = new ArrayList<Tag>();

        Statement stmt = getConnection().createStatement();
        ResultSet query = stmt.executeQuery("SELECT tag.id, tag.name FROM activity_to_tag, tag WHERE activity_to_tag.activity='"+activity_id+"' AND tag.id=activity_to_tag.tag");
        while (query.next()) {
            res.add(new Tag(query.getInt("id"), query.getString("name")));
        }

        return res;
    }
}