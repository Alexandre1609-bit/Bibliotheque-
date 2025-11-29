package com.alex.bibliotheque_web.dao;

import com.alex.bibliotheque_web.model.Book;
import com.alex.bibliotheque_web.model.Loan;
import com.alex.bibliotheque_web.model.User;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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


@Repository
public class LoanDAO {

    private final DataSource dataSource;

    public LoanDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setLoan(Loan loan) {

        try {

            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO loans (borrow_date, return_date, books_id_fk, user_id_fk) VALUES (?, ?, ?, ?)";

            PreparedStatement pStatement = connection.prepareStatement(sql);

            //Rajout d'une sécurité pour la date afin d'éviter les erreurs :java.lang.NullPointerException: Cannot invoke "java.time.LocalDate.getYear()" because "date" is null/

            LocalDate dateEmprunt = loan.getBorrowDate();
            if (dateEmprunt == null) {
                System.out.println("Attention : Date reçue nulle, utilisation de la date du jour.");
                dateEmprunt = LocalDate.now();
            }

            LocalDate dateRetour = loan.getReturnDate();
            if (dateRetour == null) {
                dateRetour = dateEmprunt.plusDays(7);
            }

            //On remplace les ancients statements par ceux sécurisés
            pStatement.setDate(1, java.sql.Date.valueOf(dateEmprunt));
            pStatement.setDate(2, java.sql.Date.valueOf(dateRetour));

            pStatement.setInt(3, loan.getTheBook().getBook_id());
            pStatement.setInt(4, loan.getBorrowUser().getId());

            pStatement.executeUpdate();
            System.out.println("Emprunt enregistré");

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBook(Loan loanToClose) {

        try {
            Connection connection = dataSource.getConnection();
            String sql = "UPDATE loans SET return_date_real = ? WHERE loan_id = ?";

            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setDate(1, Date.valueOf(LocalDate.now()));
            pStatement.setInt(2, loanToClose.getId());

            pStatement.executeUpdate();
            System.out.println("Livre rendu avec succès");

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Loan> findLoansByUserId(int userId) {
        List<Loan> loans = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();

            // La nouvelle requête SQL avec le JOIN !!
            String sql = "SELECT \n" +
                    "    loans.loan_id, \n" +
                    "    loans.borrow_date, \n" +
                    "    loans.return_date, \n" +
                    "    loans.return_date_real, \n" +
                    "    books.books_id, \n" +
                    "    books.title, \n" +
                    "    books.author, \n" +
                    "    books.img_link \n" +
                    "FROM loans \n" +
                    "JOIN books ON loans.books_id_fk = books.books_id \n" +
                    "WHERE loans.user_id_fk = ?";

            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, userId);

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) { // Tant qu'il y a des résultats
                // On reconstruit le livre
                Book book = new Book();
                book.setBook_id(rs.getInt("books_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setImg_link(rs.getString("img_link"));

                // Pareil avec l'utilisateur (juste l'id)
                User user = new User();
                user.setId(userId);

                // On gère nos dates
                java.sql.Date sqlBorrowDate = rs.getDate("borrow_date");
                java.sql.Date sqlReturnDate = rs.getDate("return_date");
                java.sql.Date sqlReturnReal = rs.getDate("return_date_real");

                LocalDate borrowDate = (sqlBorrowDate != null) ? sqlBorrowDate.toLocalDate() : null;
                LocalDate returnDate = (sqlReturnDate != null) ? sqlReturnDate.toLocalDate() : null;
                LocalDate returnReal = (sqlReturnReal != null) ? sqlReturnReal.toLocalDate() : null;

                // On reconstruit l'emprunt
                Loan loan = new Loan(
                        rs.getInt("loan_id"),
                        borrowDate,
                        returnDate,
                        returnReal,
                        user,
                        book
                );

                loans.add(loan);
            }

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loans;
    }
}


