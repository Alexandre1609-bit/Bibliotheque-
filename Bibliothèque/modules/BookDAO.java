package modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Une classe "Data Acces Object" pour communiquer avec ma classe books
public class BookDAO {

    public List<Book> findAll() {
        ArrayList<Book> books = new ArrayList<>();

        try {
            Connection connect = DatabaseConnection.getConnection();
            Statement vehicule = connect.createStatement();

            String requete = "SELECT * FROM books";
            ResultSet results = vehicule.executeQuery(requete);

            while (results.next()) {
                String titre = results.getString("title");
                String auteur = results.getString("author");
                int stock = results.getInt("stock");
                int id = results.getInt("books_id");


                Book b = new Book(titre, auteur, stock, id);

                books.add(b);
            }

            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}