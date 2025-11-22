package modules;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- TEST DU DAO ---");
        BookDAO bookManager = new BookDAO();
        UserDAO userManager = new UserDAO();
        LoanDAO loanManager = new LoanDAO();

        // 1. CRÉATION UTILISATEUR
        User[] nouveauUSer = {
                new User(0, "Thomas", "thomas" + System.currentTimeMillis() + "@gmail.com", "test")
        };

        for (User u : nouveauUSer) {
            // On enregistre et on récupère le VRAI ID !!
            int trueID = userManager.addUser(u);
            u.id = trueID; // L'objet 'u' est maintenant synchronisé avec la base (donc id correct).
            System.out.println("Utilisateur " + u.name + " enregistré avec l'Id n° : " + u.id);
        }

        // 2. RÉCUPÉRATION DES LIVRES
        List<Book> mesLivres = bookManager.findAll();
        System.out.println("J'ai trouvé " + mesLivres.size() + " livres.");

        if (mesLivres.isEmpty()) return; // Sécurité si la base est vide

        // 3. TEST DE CONNEXION
        System.out.println("--- Test de connexion ---");
        User userConnecte = userManager.connect(nouveauUSer[0].email, "test");

        if (userConnecte != null) {
            System.out.println("Connexion réussie pour " + userConnecte.name);
        } else {
            System.out.println("Echec de connexion");
        }

        // 4. CRÉATION DE L'EMPRUNT
        // On prend l'utilisateur 0 du tableau
        // On prend le livre 0 de la liste
        System.out.println("--- Création de l'emprunt ---");

        Loan monEmprunt = new Loan(
                0,
                LocalDate.now(),
                nouveauUSer[0],
                mesLivres.get(0)
        );

        //On appelle le DAO pour sauvegarder !!!
        loanManager.setLoan(monEmprunt);
    }
}