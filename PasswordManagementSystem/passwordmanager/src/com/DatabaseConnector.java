package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/passwordmanagerdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connection established successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database: " + e.getMessage());
            }
        }
        return connection;
    }
}
