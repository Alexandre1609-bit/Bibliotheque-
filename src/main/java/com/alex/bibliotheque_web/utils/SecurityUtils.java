package com.alex.bibliotheque_web.utils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


public class SecurityUtils {

    public static String hashPassword(String passwordToHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodeHash = digest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));

            //Il faut convertir les bytes en hexadécimal
            StringBuilder hexString = new StringBuilder(2 * encodeHash.length);
            for (int i = 0; i < encodeHash.length; i++) {
                String hex = Integer.toHexString(0xff & encodeHash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



/**
 * Hash un mot de passe en utilisant l'algorithme SHA-256.
 * * Fonctionnement :
 * 1. On récupère l'instance de l'algo SHA-256 (MessageDigest).
 * 2. On transforme le mot de passe (String) en tableau d'octets (byte[]) car l'algo ne traite que des nombres.
 * 3. On "digère" (hash) ces octets. Le résultat est illisible (binaire).
 * 4. On boucle sur chaque octet pour le convertir en Hexadécimal (Base 16) lisible :
 * On applique un masque (0xff) pour gérer les nombres négatifs.
 * On rajoute un '0' devant les chiffres uniques (ex : 'A' devient '0A') pour garder le format standard.
 * * @param passwordToHash Le mot de passe en clair
 * @return L'empreinte de 64 caractères (le hash)
 */