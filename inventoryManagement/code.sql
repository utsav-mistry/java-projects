-- Create a new database called 'inventory'
CREATE DATABASE inventory;

-- Create a table 'items' to store item details
CREATE TABLE items (
    item_id INT PRIMARY KEY,  
    name VARCHAR(100),        
    quantity INT,             
    price DECIMAL(10, 2)      
);

-- Create a table 'orders' to store order details
CREATE TABLE orders (
    order_id INT PRIMARY KEY,  
    item_id INT,               
    quantity INT,              
    order_date DATE,           
    FOREIGN KEY (item_id) REFERENCES items(item_id)  
);

-- Create a table 'sales' to store sales details
CREATE TABLE sales (
    sale_id INT PRIMARY KEY,   
    item_id INT,               
    quantity INT,              
    sale_date DATE,            
    FOREIGN KEY (item_id) REFERENCES items(item_id)  
);

-- Create a table 'deliveries' to store delivery details
CREATE TABLE deliveries (
    delivery_id INT PRIMARY KEY,  
    item_id INT,                  
    quantity INT,                 
    delivery_date DATE,           
    FOREIGN KEY (item_id) REFERENCES items(item_id)  
);
