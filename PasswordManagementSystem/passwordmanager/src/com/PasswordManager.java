package com;

import java.sql.*;

import utils.EncryptionUtils;
import utils.MyLinkedList;

public class PasswordManager {
    private final MyLinkedList<String> accessLog = new MyLinkedList<>();
    private final Connection connection;

    public PasswordManager(Connection connection) {
        this.connection = connection;
    }

    public void savePassword(String website, String username, String password, String shaKey) {
        String aesKey = EncryptionUtils.hashSHA256(shaKey);
        String encryptedPassword = EncryptionUtils.encrypt(password, aesKey);

        try {
            String query = "INSERT INTO users (website, username, encrypted_password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, website);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, encryptedPassword);
            preparedStatement.executeUpdate();

            accessLog.add("Saved password for " + website + " (" + username + ")");
        } catch (SQLException e) {
            System.out.println("Error saving password: " + e.getMessage());
        }
    }

    public void retrievePassword(String website, String username, String shaKey) {
        String aesKey = EncryptionUtils.hashSHA256(shaKey);

        try {
            String query = "SELECT encrypted_password FROM users WHERE website = ? AND username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, website);
            preparedStatement.setString(2, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String encryptedPassword = resultSet.getString("encrypted_password");
                String decryptedPassword = EncryptionUtils.decrypt(encryptedPassword, aesKey);

                System.out.println("Website: " + website);
                System.out.println("Username: " + username);
                System.out.println("Password: " + decryptedPassword);

                accessLog.add("Retrieved password for " + website + " (" + username + ")");
            } else {
                System.out.println("No saved password found for " + website);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving password: " + e.getMessage());
        }
    }

    public void updatePassword(String website, String username, String newPassword, String shaKey) {
        String aesKey = EncryptionUtils.hashSHA256(shaKey);
        String encryptedPassword = EncryptionUtils.encrypt(newPassword, aesKey);

        try {
            String query = "UPDATE users SET encrypted_password = ? WHERE website = ? AND username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, encryptedPassword);
            preparedStatement.setString(2, website);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();

            accessLog.add("Updated password for " + website + " (" + username + ")");
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }

    public void showAccessLog() {
        System.out.println("Access Log:");
        for (String logEntry : accessLog) {
            System.out.println(logEntry);
        }
    }

    public void deletePassword(String website, String username) {
        try {
            String query = "DELETE FROM users WHERE website = ? AND username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, website);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            accessLog.add("Deleted password for " + website + " (" + username + ")");
        } catch (SQLException e) {
            System.out.println("Error deleting password: " + e.getMessage());
        }
    }
}