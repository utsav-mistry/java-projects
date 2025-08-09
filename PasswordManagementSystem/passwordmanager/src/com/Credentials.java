package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utils.EncryptionUtils;

public class Credentials {
    private static final String CREDENTIALS_FILE = "credentials.txt";
    private static final String ENCRYPTION_KEY = "encryptionKey"; // Key for AES encryption

    public static boolean areCredentialsSet() {
        return readStoredCredentials() != null;
    }

    public static void setCredentials(String username, String encryptedPassword) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE))) {
            writer.write(username + "\n" + encryptedPassword);
        } catch (IOException e) {
            throw new RuntimeException("Error saving credentials", e);
        }
    }

    public static boolean verifyCredentials(String username, String password) {
        String[] storedCredentials = readStoredCredentials();
        if (storedCredentials == null) return false;

        String storedUsername = storedCredentials[0];
        String storedEncryptedPassword = storedCredentials[1];
        String hashedPassword = EncryptionUtils.hashSHA256(password);
        String encryptedPassword = EncryptionUtils.encrypt(hashedPassword, ENCRYPTION_KEY);

        return username.equals(storedUsername) && encryptedPassword.equals(storedEncryptedPassword);
    }

    public static void updatePassword(String currentPassword, String newPassword) {
        if (verifyCredentials(readStoredCredentials()[0], currentPassword)) {
            String encryptedNewPassword = EncryptionUtils.encrypt(EncryptionUtils.hashSHA256(newPassword), ENCRYPTION_KEY);
            setCredentials(readStoredCredentials()[0], encryptedNewPassword);
            System.out.println("Master password updated successfully.");
        } else {
            System.out.println("Old password is incorrect.");
        }
    }

    private static String[] readStoredCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String username = reader.readLine();
            String encryptedPassword = reader.readLine();
            return new String[]{username, encryptedPassword};
        } catch (IOException e) {
            return null;
        }
    }
}
