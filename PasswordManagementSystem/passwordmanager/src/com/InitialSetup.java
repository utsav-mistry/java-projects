package com;

import utils.EncryptionUtils;
import java.util.Scanner;

public class InitialSetup {
    public static void initialSetup(Scanner scanner) {
        try {
            System.out.print("Enter new master username: ");
            String username = scanner.nextLine();
            System.out.print("Enter new master password: ");
            String password = scanner.nextLine();

            String encryptedPassword = EncryptionUtils.encrypt(EncryptionUtils.hashSHA256(password), "encryptionKey");
            Credentials.setCredentials(username, encryptedPassword);

            System.out.println("Master credentials have been set up.");
        } catch (Exception e) {
            System.out.println("An error occurred during initial setup: " + e.getMessage());
        }
        
    }
}
 