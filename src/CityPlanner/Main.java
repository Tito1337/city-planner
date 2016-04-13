package CityPlanner;

import CityPlanner.Model.*;
import java.sql.*;
/**
 * Created by tito on 5/02/16.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection db = Database.getConnection();
        Statement stmt = db.createStatement();

        City paris = new City("Paris");
        System.out.println(paris.getName());
    }
}
