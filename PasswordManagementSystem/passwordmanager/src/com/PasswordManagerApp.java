package com;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PasswordManagerApp {

    private static Scanner scanner = new Scanner(System.in); // Class field for Scanner

    public static void main(String[] args) {
        Connection connection = null;

        try {
            if (!Credentials.areCredentialsSet()) {
                InitialSetup.initialSetup(scanner); // Pass the scanner to avoid closing it
            }

            DatabaseConnector dbConnector = new DatabaseConnector();
            try {
                connection = dbConnector.getConnection();
                if (connection == null) {
                    System.out.println("Failed to connect to the database. Exiting...");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Database connection error: " + e.getMessage());
                return;
            }

            PasswordManager manager = new PasswordManager(connection);

            if (!authenticateUser()) { // Updated method call
                System.out.println("Authentication failed. Exiting...");
                return;
            }

            while (true) {
                System.out.println("Password Manager Menu:");
                System.out.println("1. Save Password");
                System.out.println("2. Retrieve Password");
                System.out.println("3. Update Password");
                System.out.println("4. Delete Password");
                System.out.println("5. Show Access Log");
                System.out.println("6. Change Master Password");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");

                int choice = 0;
                while (true) {
                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline left-over
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                }

                switch (choice) {
                    case 1:
                        handleSavePassword(manager);
                        break;
                    case 2:
                        handleRetrievePassword(manager);
                        break;
                    case 3:
                        handleUpdatePassword(manager);
                        break;
                    case 4:
                        handleDeletePassword(manager);
                        break;
                    case 5:
                        manager.showAccessLog();
                        break;
                    case 6:
                        changeMasterPassword();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    private static boolean authenticateUser() {
        while (true) {
            try {
                System.out.print("Enter master username: ");
                String username = scanner.nextLine().trim(); // Use the class-level scanner

                System.out.print("Enter master password: ");
                String password = scanner.nextLine().trim(); // Use the class-level scanner

                return Credentials.verifyCredentials(username, password);
            } catch (NoSuchElementException e) {
                System.out.println("Input error. Please try again.");
                scanner.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    // Adjust other methods to use the class Scanner instance if needed

    private static void handleSavePassword(PasswordManager manager) {
        try {
            System.out.print("Enter website: ");
            String website = scanner.nextLine().trim();
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            manager.savePassword(website, username, password, "mainEntryPassword");
        } catch (Exception e) {
            System.out.println("An error occurred while saving the password: " + e.getMessage());
        }
    }

    private static void handleRetrievePassword(PasswordManager manager) {
        try {
            System.out.print("Enter website: ");
            String website = scanner.nextLine().trim();
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            manager.retrievePassword(website, username, "mainEntryPassword");
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving the password: " + e.getMessage());
        }
    }

    private static void handleUpdatePassword(PasswordManager manager) {
        try {
            System.out.print("Enter website: ");
            String website = scanner.nextLine().trim();
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine().trim();
            manager.updatePassword(website, username, newPassword, "mainEntryPassword");
        } catch (Exception e) {
            System.out.println("An error occurred while updating the password: " + e.getMessage());
        }
    }

    private static void handleDeletePassword(PasswordManager manager) {
        try {
            System.out.print("Enter website: ");
            String website = scanner.nextLine().trim();
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            manager.deletePassword(website, username);
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the password: " + e.getMessage());
        }
    }

    private static void changeMasterPassword() {
        while (true) {
            try {
                System.out.println("Change Master Password");
                System.out.print("Enter current master password: ");
                String currentPassword = scanner.nextLine().trim();

                System.out.print("Enter new master password: ");
                String newPassword = scanner.nextLine().trim();

                System.out.print("Re-enter new master password: ");
                String reenteredPassword = scanner.nextLine().trim();

                if (newPassword.equals(reenteredPassword)) {
                    Credentials.updatePassword(currentPassword, newPassword);
                    System.out.println("Master password updated successfully.");
                    return;
                } else {
                    System.out.println("New passwords do not match.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while changing the master password: " + e.getMessage());
            }
        }
    }
}
