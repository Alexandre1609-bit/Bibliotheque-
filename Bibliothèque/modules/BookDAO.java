package modules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Une classe "Data Acces Object" pour communiquer avec ma classe books
/* Le DAO a plusieurs utilités : Avec un DAO on peut juste modifier son code pour changer de base de donnée par exemple.
   Pas besoin de modifier le "main" et pas besoin de tout réécrire !
   La réutilisabilité !! --> Avec un petit projet comme le miens qui n'a que le "main" pour le moment ça peut aller mais
   si j'agrandis j'aurais : Une application console, un site et une appli mobile. C'est 3 programmes doivent tous afficher
   la liste des livres. Si le SQL est dans le "Main" le site web ne peut pas l'utiliser, je vais devoir copier-coller et ça
   sera propice aux erreurs. Au contraire, s'il est dans le DAO, les 3 programmes appellent simplement les méthodes
   (style "new BookDAO().findAll()", j'écris le code une fois. Outre cela, c'est plus pratique pour optimiser et changer
   des choses. Sans DAO : Je dois chercher dans tous mes fichiers pour trouver tous les endroits où j'ai écrit "SELECT"
   par exemple. Avec le DAO je dois juste changer la ligne à un seul endroit
*/

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

    public void addBook(Book bookToAdd) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO books (title, author, stock) VALUES (?, ?, ?)";

            PreparedStatement pStatement = connection.prepareStatement(sql);

            // NB: pas possible de faire un pStatement.setarray (ou setString...)"rapidement", il faut faire un par un.
            // Exemple à ne pas faire :
            // pStatement.setString(1, bookToAdd.title, bookToAdd.author, bookToAdd.stock);
            pStatement.setString(1, bookToAdd.title);
            pStatement.setString(2, bookToAdd.author);
            pStatement.setInt(3, bookToAdd.stock);

            pStatement.executeUpdate();
            System.out.println("Livre ajouté avec succès");

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book updateBook) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE books SET title = ?, author = ?, stock = ? WHERE books_id = ?";

            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, updateBook.title);
            pStatement.setString(2, updateBook.author);
            pStatement.setInt(3, updateBook.stock);
            pStatement.setInt(4, updateBook.book_id);

            pStatement.executeUpdate();
            System.out.println("Livre modifié avec succès");

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void deleteBook(Book bookToDelete) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM books WHERE books_id = ?";

            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, bookToDelete.book_id);
            pStatement.executeUpdate();

            System.out.println("Livre supprimé avec succès");

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}