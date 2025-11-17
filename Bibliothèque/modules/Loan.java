import java.time.LocalDate;


public class Loan {

    int id;
    LocalDate borrowDate;
    LocalDate returnDate;
    LocalDate trueReturnDate;
    User borrowUser;
    Book theBook;

    public Loan(int id, LocalDate borrowDate, LocalDate returnDate, LocalDate trueReturnDate, User borrowUser, Book theBook) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.trueReturnDate = trueReturnDate;
        this.borrowUser = borrowUser;
        this.theBook = theBook;
    }
}