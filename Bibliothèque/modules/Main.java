import java.time.LocalDate; // <-- IMPORTANT: A ajouter en haut de ton Main.java

public class Main {
    public static void main(String[] args) {


        Book livreDune = new Book("Dune", "Frank Herbert", 5, 101);
        User utilisateurJean = new User(1, "Jean Dupont", "jean@email.com", "motdepasse123");


        LocalDate dateEmprunt = LocalDate.now();
        LocalDate dateRetourPrevue = dateEmprunt.plusDays(14);
        LocalDate dateRetourReelle = null;


        Loan premierEmprunt = new Loan(1, dateEmprunt, dateRetourPrevue, dateRetourReelle, utilisateurJean, livreDune);


        System.out.println("L'utilisateur qui a emprunté le livre est : " + premierEmprunt.borrowUser.name);
        System.out.println("Le titre du livre emprunté est : " + premierEmprunt.theBook.name);
        System.out.println("Date d'emprunt : " + premierEmprunt.borrowDate);
        System.out.println("Date de retour prévue : " + premierEmprunt.returnDate);


    }
}