package com.alex.bibliotheque_web.controller;


import com.alex.bibliotheque_web.dao.LoanDAO;
import com.alex.bibliotheque_web.dao.BookDAO;
import com.alex.bibliotheque_web.service.LoanService;
import com.alex.bibliotheque_web.model.Loan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    private final LoanDAO loanDAO;
    private final BookDAO bookDAO;
    private final LoanService loanService;


    public LoanController (LoanDAO loanDAO, BookDAO bookDAO, LoanService loanService) {
        this.loanDAO = loanDAO;
        this.bookDAO = bookDAO;
        this.loanService = loanService;
    }

    @PostMapping("/emprunts")
    public void createLoan(@RequestBody Loan loan) {
        loanService.createLoan(loan);
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
