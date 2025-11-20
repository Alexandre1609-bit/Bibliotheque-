package modules;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- TEST DU DAO ---");

        BookDAO bookManager = new BookDAO();

        List<Book> mesLivres = bookManager.findAll();

        System.out.println("J'ai trouvé " + mesLivres.size() + " livres dans la base");

        for (Book livre : mesLivres) {
            System.out.println("- [" + livre.book_id + "] " + livre.title + " (" + livre.stock + " en stock)");
        }
    }
}