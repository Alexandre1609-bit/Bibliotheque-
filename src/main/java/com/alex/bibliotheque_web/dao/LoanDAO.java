package com.alex.bibliotheque_web.dao;

import com.alex.bibliotheque_web.model.Loan;
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

    public void returnBook (Loan loanToClose) {

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

}
