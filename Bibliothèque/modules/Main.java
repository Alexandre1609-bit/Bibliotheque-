package modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
// On n'utilise pas LocalDate pour l'instant dans ce test, mais tu peux le laisser

public class Main {
    public static void main(String[] args) {

        System.out.println("--- DÉBUT DU PROGRAMME ---");

        try {

            Connection maConnexion = DatabaseConnection.getConnection();

            if (maConnexion == null) {
                return;
            }

            //Requête
            String sql = "SELECT * FROM books";

            //Préparation
            Statement monStatement = maConnexion.createStatement();

            //Exécution et Récupération
            ResultSet resultats = monStatement.executeQuery(sql);

            System.out.println("\n📚 LISTE DES LIVRES (Depuis MySQL) :");

            //Lecture (Boucle)
            while (resultats.next()) {
                String titre = resultats.getString("title");
                String auteur = resultats.getString("author");
                int stock = resultats.getInt("stock");


                System.out.println("- " + titre + " (écrit par " + auteur + ") - Stock : " + stock);
            }


            maConnexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n--- FIN DU PROGRAMME ---");
    }
}