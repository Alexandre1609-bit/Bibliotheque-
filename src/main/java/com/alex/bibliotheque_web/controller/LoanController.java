package com.alex.bibliotheque_web.controller;


import com.alex.bibliotheque_web.dao.LoanDAO;
import com.alex.bibliotheque_web.model.Loan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    private final LoanDAO loanDAO;

    public LoanController (LoanDAO loanDAO) {this.loanDAO = loanDAO;}

    @PostMapping("/emprunts")
    public void createLoan(@RequestBody Loan loan) {
        loanDAO.setLoan(loan);
    }

    @PostMapping("/retours")
    public void returnBook(@RequestBody Loan bookToReturn) {
        loanDAO.returnBook(bookToReturn);
    }
}
