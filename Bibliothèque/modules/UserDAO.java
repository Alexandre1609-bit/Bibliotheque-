package modules;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modules.SecurityUtils;


public class UserDAO {


    public int addUser(User user) {
        int generateId = -1; //Valeur par défaut, une erreur

        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

            //Ajout du flag "RETURN_GENERATED_KEYS" pour surveiller l'ID !!!
            PreparedStatement pStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pStatement.setString(1, user.name);
            pStatement.setString(2, user.email);
            pStatement.setString(3, SecurityUtils.hashPassword(user.pswd));
            pStatement.executeUpdate();

            //On récupère les clés !!
            ResultSet rs = pStatement.getGeneratedKeys();

            if (rs.next()) {
                //La colonne n°1 du résultat est le nouvel ID
                generateId = rs.getInt(1);
            }

            System.out.println("Utilisateur ajouté avec l'Id : " + generateId);
            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return generateId;
    }

    public void anonymizeUser(User userToAnonymize) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE user_id = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, "Anonyme");
            pStatement.setString(2, "supprimé" + userToAnonymize.id);
            pStatement.setString(3, "");
            pStatement.setInt(4, userToAnonymize.id);
            pStatement.executeUpdate();

            System.out.println("Utilisateur supprimé");

            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User connect (String email, String password) {
        String securePswd = SecurityUtils.hashPassword(password);

        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, email);
            pStatement.setString(2, securePswd);

            ResultSet result = pStatement.executeQuery();


            if (result.next()) {
                int idTrouve = result.getInt("user_id");
                String nomTrouve = result.getString("name");
                String emailTrouve = result.getString("email");
                String mdpTrouve = result.getString("password");

                pStatement.close();
                connection.close();

                return new User(idTrouve, nomTrouve, emailTrouve, mdpTrouve);

            } else {
                System.out.println("Identifiants incorrects");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
