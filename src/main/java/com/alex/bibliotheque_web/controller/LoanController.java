package com.alex.bibliotheque_web.controller;


import com.alex.bibliotheque_web.dao.LoanDAO;
import com.alex.bibliotheque_web.dao.BookDAO;
import com.alex.bibliotheque_web.model.Loan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    private final LoanDAO loanDAO;
    private final BookDAO bookDAO;


    public LoanController (LoanDAO loanDAO, BookDAO bookDAO) {
        this.loanDAO = loanDAO;
        this.bookDAO = bookDAO;}

    @PostMapping("/emprunts")
    public void createLoan(@RequestBody Loan loan) {
        loanDAO.setLoan(loan);

        //MAJ du stock
        bookDAO.stockUpdate(loan.getTheBook().getBook_id(), -1);
    }

    @PostMapping("/retours")
    public void returnBook(@RequestBody Loan loan) {
        loanDAO.returnBook(loan);

        Integer bookId = loan.getTheBook().getBook_id();
        bookDAO.stockUpdate(bookId, 1);
    }

    @GetMapping("/emprunts")
    public List<Loan> getLoanByUser(@RequestParam int userId) {
        return loanDAO.findLoansByUserId(userId);
    }
}
