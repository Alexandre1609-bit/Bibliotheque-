package com.alex.bibliotheque_web.service;
import com.alex.bibliotheque_web.dao.BookDAO;
import com.alex.bibliotheque_web.dao.LoanDAO;
import com.alex.bibliotheque_web.model.Book;
import com.alex.bibliotheque_web.model.Loan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    private final LoanDAO loanDAO;
    private final BookService bookService;
    private final BookDAO bookDAO;

    public LoanService(LoanDAO loanDAO, BookDAO bookDAO, BookService bookService) {
        this.loanDAO = loanDAO;
        this.bookService = bookService;
        this.bookDAO = bookDAO;
    }

    public void createLoan(Loan loan) { //Fait la partie vérification de ma méthode setLoan

        //Rajout d'une sécurité pour la date afin d'éviter les erreurs :java.lang.NullPointerException: Cannot invoke "java.time.LocalDate.getYear()" because "date" is null/

        LocalDate dateEmprunt = loan.getBorrowDate();
        if (dateEmprunt == null) {
            System.out.println("Attention : Date reçue nulle, utilisation de la date du jour.");
            loan.setBorrowDate(LocalDate.now());
        }

        LocalDate dateRetour = loan.getReturnDate();
        if (dateRetour == null) {
            loan.setReturnDate(loan.getBorrowDate().plusDays(7));
        }

        int idLivre = loan.getTheBook().getBook_id();
        Book livreComplet = bookService.findBookById(idLivre);

        if (livreComplet.getStock() > 0) {

            loanDAO.setLoan(loan);

            bookDAO.stockUpdate(loan.getTheBook().getBook_id(), -1);
        } else {
            throw new RuntimeException("Impossible d'emprunter : Le stock est vide !");
        }
    }
}


