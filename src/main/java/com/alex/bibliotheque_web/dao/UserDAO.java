package com.alex.bibliotheque_web.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alex.bibliotheque_web.model.User;
import com.alex.bibliotheque_web.utils.SecurityUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class UserDAO {

    private final DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int addUser(User user) {
        int generateId = -1;

        // 1. Préparation de la cuisine (Sel + Hash)
        String salt = SecurityUtils.generateSalt();
        String hash = SecurityUtils.hashPassword(user.getPswd(), salt); // On utilise le sel généré

        try {
            Connection connection = dataSource.getConnection();

            // CORRECTION 1 : 4 colonnes = 4 points d'interrogation
            String sql = "INSERT INTO users (name, email, password, salt) VALUES (?, ?, ?, ?)";

            PreparedStatement pStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pStatement.setString(1, user.getName());
            pStatement.setString(2, user.getEmail());
            pStatement.setString(3, hash);
            pStatement.setString(4, salt);

            pStatement.executeUpdate();

            ResultSet rs = pStatement.getGeneratedKeys();
            if (rs.next()) {
                generateId = rs.getInt(1);
            }

            System.out.println("Compté crée avec succès");
            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return generateId;
    }

    public void anonymizeUser(User userToAnonymize) {
        try {
            Connection connection = dataSource.getConnection();
            String sql = "UPDATE users SET name = ?, email = ?, password = ?, salt = NULL WHERE user_id = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, "Anonyme");
            pStatement.setString(2, "supprimé" + userToAnonymize.getId());
            pStatement.setString(3, "");
            pStatement.setInt(4, userToAnonymize.getId());
            pStatement.executeUpdate();

            System.out.println("Utilisateur supprimé");

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User connect(String email, String passwordInput) {
        // On ne peut pas hacher tout de suite ici, car on n'a pas encore le sel !!!!!

        try {
            Connection connection = dataSource.getConnection();

            // On cherche l'utilisateur SEULEMENT avec l'email, plus l'email Et le mdp
            String sql = "SELECT * FROM users WHERE email = ?";

            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, email);
            //Retrait du setString(2) car on ne vérifie pas le mdp tout de suite

            ResultSet result = pStatement.executeQuery();

            if (result.next()) {
                // L'utilisateur existe ! Maintenant vérification du mot de passe.

                //On récupère le sel et le mot de passe (hash) stockés en base
                String storedSalt = result.getString("salt");
                String storedHash = result.getString("password");

                //On calcule le hash de ce que l'utilisateur vient de taper + le sel trouvé
                String calculatedHash = SecurityUtils.hashPassword(passwordInput, storedSalt);

                // 4. On compare
                if (calculatedHash.equals(storedHash)) {
                    //On récupère le reste des infos
                    int idTrouve = result.getInt("user_id");
                    String nomTrouve = result.getString("name");
                    String emailTrouve = result.getString("email");

                    pStatement.close();
                    connection.close();

                    return new User(idTrouve, nomTrouve, emailTrouve, storedHash);
                } else {
                    // Le hash calculé ne correspond pas au hash stocké :
                    System.out.println("Mot de passe incorrect");
                    pStatement.close();
                    connection.close();
                    return null;
                }

            } else {
                System.out.println("Email inconnu");
                pStatement.close();
                connection.close();
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
