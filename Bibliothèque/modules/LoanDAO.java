package modules;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    public void setLoan(Loan loan) {

        try {

        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO loans (borrow_date, return_date, books_id_fk, user_id_fk) VALUES (?, ?, ?, ?)";

        PreparedStatement pStatement = connection.prepareStatement(sql);
        pStatement.setDate(1, java.sql.Date.valueOf(loan.borrowDate));
        pStatement.setDate(2, java.sql.Date.valueOf(loan.returnDate));
        pStatement.setInt(3, loan.theBook.book_id);
        pStatement.setInt(4, loan.borrowUser.id);

        pStatement.executeUpdate();
        System.out.println("Emprunt enregistré");

        pStatement.close();
        connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBook (Loan loanToClose) {

        try {

        }
    }

}
