package modules;
import java.time.LocalDate;


public class Loan {

    int id;
    LocalDate borrowDate;
    LocalDate returnDate;
    LocalDate trueReturnDate;
    User borrowUser;
    Book theBook;

    public Loan(int id, LocalDate borrowDate, User borrowUser, Book theBook) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.plusDays(7);
        this.trueReturnDate = null;
        this.borrowUser = borrowUser;
        this.theBook = theBook;
    }

    //Overload (Surcharge) Avoir plusieurs méthodes (ou constructeurs) avec le même nom dans la même classe !
    //Mais avec des paramètres différents !! Parfait dans ce cas.

    public Loan(int id, LocalDate borrowDate, LocalDate returnDate, LocalDate trueReturnDate, User borrowUser, Book theBook) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.trueReturnDate = trueReturnDate;
        this.borrowUser = borrowUser;
        this.theBook = theBook;
    }
}