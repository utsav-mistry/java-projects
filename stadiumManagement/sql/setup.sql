-- Create the database
CREATE DATABASE IF NOT EXISTS stadium_management;
USE stadium_management;

-- Create events table
CREATE TABLE IF NOT EXISTS events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    description TEXT
);

-- Create tickets table
CREATE TABLE IF NOT EXISTS tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    customer_id INT NOT NULL,
    seat_number VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(event_id)
);

-- Create customers table
CREATE TABLE IF NOT EXISTS customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20)
);

-- Create venues table
CREATE TABLE IF NOT EXISTS venues (
    venue_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
);

-- Create event_venues table
CREATE TABLE IF NOT EXISTS event_venues (
    event_id INT,
    venue_id INT,
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (venue_id) REFERENCES venues(venue_id),
    PRIMARY KEY (event_id, venue_id)
);
