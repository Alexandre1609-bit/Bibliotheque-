package com.alex.bibliotheque_web.controller;


import com.alex.bibliotheque_web.dao.LoanDAO;
import com.alex.bibliotheque_web.dao.BookDAO;
import com.alex.bibliotheque_web.service.LoanService;
import com.alex.bibliotheque_web.model.Loan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {
    private final LoanService loanService;


    public LoanController (LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/emprunts")
    public void createLoan(@RequestBody Loan loan) {
        loanService.createLoan(loan);
    }

    @PostMapping("/retours")
    public void returnBook(@RequestBody Loan loan) {
        loanService.returnLoan(loan);
    }

    @GetMapping("/emprunts")
    public List<Loan> getLoanByUser(@RequestParam int userId) {
        return loanService.findLoansByUserId(userId);
    }
}
