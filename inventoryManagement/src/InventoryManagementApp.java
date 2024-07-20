import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class InventoryManagementApp {

    public static void main(String[] args) {
        try {
            Connection con = DBConnection.connect();
            Scanner sc = new Scanner(System.in);
            ItemDAO itemDAO = new ItemDAO(con);
            OrderDAO orderDAO = new OrderDAO(con);
            boolean exit = false;

            while (!exit) {
                System.out.println("\nInventory Management System");
                System.out.println("1. Add Item");
                System.out.println("2. Update Item Quantity");
                System.out.println("3. Get Item");
                System.out.println("4. Get All Items");
                System.out.println("5. Update Item");
                System.out.println("6. Delete Item");
                System.out.println("7. Add Order");
                System.out.println("8. Get Order");
                System.out.println("9. Get All Orders");
                System.out.println("10. Update Order");
                System.out.println("11. Delete Order");
                System.out.println("12. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Item ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter Item Name: ");
                        String name = sc.next();
                        System.out.print("Enter Item Quantity: ");
                        int quantity = sc.nextInt();
                        System.out.print("Enter Item Price: ");
                        double price = sc.nextDouble();
                        Item newItem = new Item(id, name, quantity, price);
                        itemDAO.addItem(newItem);
                        System.out.println("Item added successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Item ID to update quantity: ");
                        int updateId = sc.nextInt();
                        System.out.print("Enter new quantity: ");
                        int newQuantity = sc.nextInt();
                        itemDAO.updateItemQuantity(updateId, newQuantity);
                        System.out.println("Item quantity updated successfully.");
                        break;

                    case 3:
                        System.out.print("Enter Item ID to fetch: ");
                        int fetchId = sc.nextInt();
                        itemDAO.getItem(fetchId);
                        break;

                    case 4:
                        itemDAO.getAllItems();
                        break;

                    case 5:
                        System.out.print("Enter Item ID: ");
                        int updateItemId = sc.nextInt();
                        System.out.print("Enter Item Name: ");
                        String updateName = sc.next();
                        System.out.print("Enter Item Quantity: ");
                        int updateQuantity = sc.nextInt();
                        System.out.print("Enter Item Price: ");
                        double updatePrice = sc.nextDouble();
                        Item updatedItem = new Item(updateItemId, updateName, updateQuantity, updatePrice);
                        itemDAO.updateItem(updatedItem);
                        System.out.println("Item updated successfully.");
                        break;

                    case 6:
                        System.out.print("Enter Item ID to delete: ");
                        int deleteItemId = sc.nextInt();
                        itemDAO.deleteItem(deleteItemId);
                        System.out.println("Item deleted successfully.");
                        break;

                    case 7:
                        System.out.print("Enter Order ID: ");
                        int orderId = sc.nextInt();
                        System.out.print("Enter Item ID: ");
                        int orderItemId = sc.nextInt();
                        System.out.print("Enter Quantity: ");
                        int orderQuantity = sc.nextInt();
                        System.out.print("Enter Order Date (YYYY-MM-DD): ");
                        String orderDate = sc.next();
                        Order newOrder = new Order(orderId, orderItemId, orderQuantity, orderDate);
                        orderDAO.addOrder(newOrder);
                        System.out.println("Order added successfully.");
                        break;

                    case 8:
                        System.out.print("Enter Order ID to fetch: ");
                        int fetchOrderId = sc.nextInt();
                        orderDAO.getOrder(fetchOrderId);
                        break;

                    case 9:
                        orderDAO.getAllOrders();
                        break;

                    case 10:
                        System.out.print("Enter Order ID: ");
                        int updateOrderId = sc.nextInt();
                        System.out.print("Enter Item ID: ");
                        int updateOrderItemId = sc.nextInt();
                        System.out.print("Enter Quantity: ");
                        int updateOrderQuantity = sc.nextInt();
                        System.out.print("Enter Order Date (YYYY-MM-DD): ");
                        String updateOrderDate = sc.next();
                        Order updatedOrder = new Order(updateOrderId, updateOrderItemId, updateOrderQuantity, updateOrderDate);
                        orderDAO.updateOrder(updatedOrder);
                        System.out.println("Order updated successfully.");
                        break;

                    case 11:
                        System.out.print("Enter Order ID to delete: ");
                        int deleteOrderId = sc.nextInt();
                        orderDAO.deleteOrder(deleteOrderId);
                        System.out.println("Order deleted successfully.");
                        break;

                    case 12:
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
