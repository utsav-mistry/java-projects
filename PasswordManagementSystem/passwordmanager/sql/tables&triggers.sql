CREATE DATABASE PasswordManagerDB;

USE PasswordManagerDB;

-- Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    website VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    encrypted_password TEXT NOT NULL
);

-- User Activity Log Table
CREATE TABLE user_activity_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Password Audit Table
CREATE TABLE password_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    old_encrypted_password TEXT NOT NULL,
    new_encrypted_password TEXT NOT NULL,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Trigger to log password changes
$$
CREATE TRIGGER password_change_trigger
AFTER UPDATE ON users
FOR EACH ROW
BEGIN
    IF OLD.encrypted_password != NEW.encrypted_password THEN
        INSERT INTO password_audit(user_id, old_encrypted_password, new_encrypted_password)
        VALUES (OLD.id, OLD.encrypted_password, NEW.encrypted_password);
    END IF;
END $$
;

-- Websites Table (Optional)
CREATE TABLE websites (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    category VARCHAR(255) DEFAULT 'General'
);

-- Trigger to log actions on user_activity_log
DELIMITER $$
CREATE TRIGGER user_activity_trigger
AFTER INSERT ON users
FOR EACH ROW
BEGIN
    INSERT INTO user_activity_log(user_id, action)
    VALUES (NEW.id, 'Account Created');
END $$;