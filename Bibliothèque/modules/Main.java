package modules;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- TEST DU DAO ---");
        BookDAO bookManager = new BookDAO();

        Book[] nouveauLivre = {
                new Book("Les chroniques de Narnia Tome 1 : Le Neveu du magicien", "C. S. Lewis.", 1, 0 ),
                new Book("Les chroniques de Narnia Tome 2 : Le Lion, la Sorcière blanche et l'Armoire magique", "C. S. Lewis.", 1, 0 )

    }; //Se renseigner pour une meilleure expérience : Batch (création de liste, une boucle pour ajouter tous les
        //livres de la liste ou faire avec "scanner"

       for (Book b : nouveauLivre) {
           bookManager.addBook(b);
       }
        List<Book> mesLivres = bookManager.findAll();

        System.out.println("J'ai trouvé " + mesLivres.size() + " livres dans la base");

        for (Book livre : mesLivres) {
            System.out.println("- [" + livre.book_id + "] " + livre.title + " (" + livre.stock + " en stock)");
        }
    }
}