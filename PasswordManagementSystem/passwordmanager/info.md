# Password Manager System

## Overview

The Password Manager System is a secure application designed to manage, store, and retrieve user passwords. It offers features such as saving, retrieving, updating, and deleting passwords, as well as changing the master password. The system ensures that all data is encrypted and stored securely.

## Features

- **Save Password**: Save passwords associated with websites and usernames.
- **Retrieve Password**: Retrieve stored passwords for specific websites and usernames.
- **Update Password**: Update existing passwords for websites and usernames.
- **Delete Password**: Remove passwords from storage.
- **Show Access Log**: Display a log of all access attempts.
- **Change Master Password**: Update the master password for the password manager.

## Components

1. **`PasswordManagerApp`**:
   - **Main Class**: Entry point of the application.
   - **Functions**:
     - Initializes the application and checks if credentials are set.
     - Connects to the database.
     - Authenticates the user.
     - Displays a menu and handles user input for various actions.

2. **`InitialSetup`**:
   - **Initial Setup Class**: Handles setting up the master username and password.
   - **Functions**:
     - Prompts for new master username and password.
     - Encrypts the password and saves it.

3. **`EncryptionUtils`**:
   - **Utility Class**: Provides methods for encryption and hashing.
   - **Functions**:
     - **`encrypt(String data, String key)`**: Encrypts data using AES encryption.
     - **`decrypt(String encryptedData, String key)`**: Decrypts data using AES encryption.
     - **`hashSHA256(String data)`**: Creates a SHA-256 hash of the input data.
     - **`generateKey(String myKey)`**: Generates a key for AES encryption.

4. **`Credentials`**:
   - **Credentials Class**: Manages user credentials.
   - **Functions**:
     - **`areCredentialsSet()`**: Checks if credentials are set.
     - **`setCredentials(String username, String encryptedPassword)`**: Sets the master username and encrypted password.
     - **`verifyCredentials(String username, String password)`**: Verifies the master username and password.
     - **`updatePassword(String currentPassword, String newPassword)`**: Updates the master password.

## Encryption Details

- **Encryption Algorithm**: AES (Advanced Encryption Standard) with ECB (Electronic Codebook) mode and PKCS5Padding.
- **Encryption Key**: A secret key derived from the provided key string.
- **Padding**: PKCS5Padding is used to handle plaintext data that isn’t a multiple of the block size.

## Hashing Details

- **Hash Algorithm**: SHA-256.
- **Hashing Process**: Converts input data into a fixed-size hash value.

## Database

- **Schema**: The database includes tables for storing user credentials and an audit log for password changes.
- **Connection**: Managed by `DatabaseConnector`.

## Usage

1. **Initial Setup**:
   - Run the application to set up the master username and password.
   
2. **Authentication**:
   - Authenticate with the master credentials to access the password management features.
   
3. **Password Management**:
   - Use the menu options to save, retrieve, update, or delete passwords.

4. **Change Master Password**:
   - Update the master password by providing the current password and the new password.

## Error Handling

- **Input Errors**: Handled with prompts for correct input.
- **Database Errors**: Includes connection and query errors.

## Notes

- Ensure to keep the encryption key secure.
- Regularly update the master password for added security.
