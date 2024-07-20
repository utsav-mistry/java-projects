# Stadium Management System - Project Overview

## Project Functions

### 1. **Event Management**
   - **Function**: Manage events within the stadium.
   - **Description**: Allows for the creation, updating, and deletion of events. Each event includes details such as the name, date, time, and description. Events are linked to specific venues through the `event_venues` table.

### 2. **Ticket Management**
   - **Function**: Handle ticket sales and information.
   - **Description**: Provides functionality for adding, updating, and deleting tickets. Each ticket is associated with an event and a customer. Ticket details include seat number and price.

### 3. **Customer Management**
   - **Function**: Manage customer information.
   - **Description**: Allows for the creation, updating, and deletion of customer records. Customer details include first name, last name, email, and phone number.

### 4. **Venue Management**
   - **Function**: Manage venue details.
   - **Description**: Handles the addition, updating, and deletion of venues. Venue information includes the name, location, and capacity.

### 5. **Event-Venue Linking**
   - **Function**: Link events to specific venues.
   - **Description**: The `event_venues` table creates a relationship between events and venues, allowing you to assign events to the appropriate locations.

## SQL Scripts

### 1. **Database Creation**
   - **Script**: Initializes the database if it does not already exist.
   - **Details**: Creates the `stadium_management` database and sets it as the current database.

### 2. **Table Creation**
   - **Script**: Defines the structure of the database.
   - **Tables**:
     - `events`: Stores event details.
     - `tickets`: Manages ticket sales and associations with events and customers.
     - `customers`: Stores customer information.
     - `venues`: Manages venue information.
     - `event_venues`: Links events to venues.

### 3. **Data Population**
   - **Script**: Inserts sample data into the tables.
   - **Details**: Adds sample records to the `events`, `tickets`, `customers`, `venues`, and `event_venues` tables to help with initial testing and development.

## How It Works

1. **Create Database and Tables**: Use the SQL scripts to set up the database and create the necessary tables.
2. **Populate Data**: Insert sample data to simulate a working system and facilitate testing.
3. **Use System Functions**: Implement Java methods to interact with the database, handle CRUD (Create, Read, Update, Delete) operations, and manage the data effectively.

## Notes

- The system assumes the absence of a user authentication mechanism as per the latest requirements.
- The current setup includes basic functionalities. Advanced features like user roles, permissions, or more complex queries can be added as needed.


## Credits

- This project is created by **Utsav Mistry**