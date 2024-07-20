# Inventory Management System (IMS)

The Inventory Management System (IMS) is designed to track and manage inventory levels, orders, sales, and deliveries for businesses. This system is built using Java programming, efficient data structures, and database management systems (DBMS). The primary objective is to streamline inventory operations, improve accuracy in tracking stock movements, and optimize inventory control processes to minimize costs and maximize profitability.

## Classes Overview

### 1. `DBConnection`
**Purpose**: Establishes a connection to the database.

**Methods**:
- `getConnection()`: Returns a `Connection` object to interact with the database.

### 2. `Item`
**Purpose**: Represents an inventory item.

**Attributes**:
- `itemId`: Unique identifier for the item.
- `name`: Name of the item.
- `quantity`: Quantity of the item in stock.
- `price`: Price of the item.

**Methods**:
- Getters and setters for each attribute.

### 3. `ItemDAO`
**Purpose**: Provides data access operations for the `Item` class.

**Attributes**:
- `connection`: Database connection object.

**Methods**:
- `addItem(Item item)`: Inserts a new item into the database.
- `updateItemQuantity(int itemId, int quantity)`: Updates the quantity of an item.
- `getItem(int itemId)`: Fetches details of an item by its ID.
- `getAllItems()`: Fetches all items from the database.

### 4. `Order`
**Purpose**: Represents an order for inventory items.

**Attributes**:
- `orderId`: Unique identifier for the order.
- `itemId`: Identifier of the item being ordered.
- `quantity`: Quantity of the item being ordered.
- `orderDate`: Date of the order.

**Methods**:
- Getters and setters for each attribute.

### 5. `OrderDAO`
**Purpose**: Provides data access operations for the `Order` class.

**Attributes**:
- `connection`: Database connection object.

**Methods**:
- `addOrder(Order order)`: Inserts a new order into the database.
- `getAllOrders()`: Fetches all orders from the database.

### 6. `InventoryManagementApp`
**Purpose**: Main application class that uses the DAOs to manage inventory and orders.

**Methods**:
- `main(String[] args)`: Entry point of the application.
  - Establishes a database connection.
  - Creates instances of `ItemDAO` and `OrderDAO`.
  - Performs various operations such as adding items, updating quantities, fetching item details, and managing orders.

## Example Workflow

### Establish Database Connection
- The `DBConnection` class provides the `getConnection` method to establish a connection to the database.

### Create and Manage Items
- The `Item` class represents an inventory item with attributes like `itemId`, `name`, `quantity`, and `price`.
- The `ItemDAO` class provides methods to add, update, and fetch items from the database.

### Create and Manage Orders
- The `Order` class represents an order with attributes like `orderId`, `itemId`, `quantity`, and `orderDate`.
- The `OrderDAO` class provides methods to add and fetch orders from the database.

### Main Application
- The `InventoryManagementApp` class demonstrates the usage of `ItemDAO` and `OrderDAO` to perform various inventory management operations.


# FILE CREATED by Utsav Mistry