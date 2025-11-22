package modules;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

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
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE loans SET return_date_real = ? WHERE loan_id = ?";

            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pStatement.setInt(2, loanToClose.id);

            pStatement.executeUpdate();
            System.out.println("Livre rendu avec succès");

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
