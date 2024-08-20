package com.example.batchtest.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UserUtils {
    /**
     * Generate a username from a name using the first and last name
     * of the person
     *
     * @param name the name to generate the username from
     * @return the generated username
     */
    public static String generateUsername(String name) {
        String[] names = name.split(" ");
        return names[0].toLowerCase() + "." + names[names.length - 1].toLowerCase();
    }

    /**
     * Encode a password using SHA-256
     *
     * @param password the password to encode
     * @return the encoded password
     */
    public static String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding password", e);
        }
    }

    /**
     * Match a raw password with an encoded password
     *
     * @param rawPassword     the raw password
     * @param encodedPassword the encoded password
     * @return true if the raw password matches the encoded password
     */
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return encodePassword(rawPassword).equals(encodedPassword);
    }

    /**
     * Check if a password is valid
     *
     * @param password the password to check
     * @return true if the password is valid
     */
    public static boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    /**
     * Generate a random password
     *
     * @return the generated password
     */
    public static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(12);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        for (int i = 0; i < 12; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
}
