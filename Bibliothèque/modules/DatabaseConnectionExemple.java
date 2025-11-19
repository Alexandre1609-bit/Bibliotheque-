package modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionExemple {

    private static final String URL = "jdbc:mysql://localhost:3306/library"; //Adresse de ma base de données

    private static final String USER = "root";
    private static final String PASSWORD = ""; //Mon mdp mis à l'installation de MySQL (rien ici en l'occurence)

    //Ouverture du tunnel
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base réussie ! ");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur : Driver manquant !");
            return null;
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
            return null;
        }
    }


}
