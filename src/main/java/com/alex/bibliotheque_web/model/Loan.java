package com.alex.bibliotheque_web.model;

import java.time.LocalDate;



public class Loan {

    private Integer id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate trueReturnDate;
    private User borrowUser;
    private Book theBook;

    public Loan(Integer id, LocalDate borrowDate, User borrowUser, Book theBook) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.plusDays(7);
        this.trueReturnDate = null;
        this.borrowUser = borrowUser;
        this.theBook = theBook;
    }

    //Overload (Surcharge) Avoir plusieurs méthodes (ou constructeurs) avec le même nom dans la même classe !
    //Mais avec des paramètres différents !! Parfait dans ce cas.

    public Loan(Integer id, LocalDate borrowDate, LocalDate returnDate, LocalDate trueReturnDate, User borrowUser, Book theBook) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.trueReturnDate = trueReturnDate;
        this.borrowUser = borrowUser;
        this.theBook = theBook;
    }

    public  Loan() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getTrueReturnDate() {
        return trueReturnDate;
    }

    public void setTrueReturnDate(LocalDate trueReturnDate) {
        this.trueReturnDate = trueReturnDate;
    }

    public User getBorrowUser() {
        return borrowUser;
    }

    public void setBorrowUser(User borrowUser) {
        this.borrowUser = borrowUser;
    }

    public Book getTheBook() {
        return theBook;
    }

    public void setTheBook(Book theBook) {
        this.theBook = theBook;
    }
}