
USE task_management;

INSERT INTO users (username, role) VALUES 
('Rajesh Shah', 'Admin'),
('Anjali Mehta', 'Manager'),
('Vikram Singh', 'Developer'),
('Priya Sharma', 'Tester'),
('Dinesh Desai', 'Designer');

INSERT INTO tasks (title, description, assigned_user, due_date, status) VALUES 
('Complete Project Plan', 'Draft the complete project plan for the new application.', 'Rajesh Shah', '2024-08-15', 'Pending'),
('Design Database Schema', 'Create the database schema for the project.', 'Anjali Mehta', '2024-08-20', 'Pending'),
('Develop Login Module', 'Implement the login module with authentication.', 'Vikram Singh', '2024-08-25', 'In Progress'),
('Test Registration Form', 'Test the registration form for all possible cases.', 'Priya Sharma', '2024-08-30', 'Pending'),
('Create UI Mockups', 'Design the UI mockups for the main screens.', 'Dinesh Desai', '2024-09-05', 'Completed');
