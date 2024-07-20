import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders (order_id, item_id, quantity, order_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getOrderId());
            stmt.setInt(2, order.getItemId());
            stmt.setInt(3, order.getQuantity());
            stmt.setString(4, order.getOrderDate());
            stmt.executeUpdate();
        }
    }

    public void getOrder(int orderId) throws SQLException {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Order ID: " + rs.getInt("order_id"));
                    System.out.println("Item ID: " + rs.getInt("item_id"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Order Date: " + rs.getString("order_date"));
                }
            }
        }
    }

    public void getAllOrders() throws SQLException {
        String query = "SELECT * FROM orders";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("Item ID: " + rs.getInt("item_id"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Order Date: " + rs.getString("order_date"));
                System.out.println("-----------");
            }
        }
    }

    public void updateOrder(Order order) throws SQLException {
        String query = "UPDATE orders SET item_id = ?, quantity = ?, order_date = ? WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getItemId());
            stmt.setInt(2, order.getQuantity());
            stmt.setString(3, order.getOrderDate());
            stmt.setInt(4, order.getOrderId());
            stmt.executeUpdate();
        }
    }

    public void deleteOrder(int orderId) throws SQLException {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }
}
