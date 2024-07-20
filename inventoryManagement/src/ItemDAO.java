import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
    private Connection connection;

    public ItemDAO(Connection connection) {
        this.connection = connection;
    }

    public void addItem(Item item) throws SQLException {
        String query = "INSERT INTO items (item_id, name, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item.getItemId());
            stmt.setString(2, item.getName());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.executeUpdate();
        }
    }

    public void updateItemQuantity(int itemId, int quantity) throws SQLException {
        String query = "UPDATE items SET quantity = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
        }
    }

    public void getItem(int itemId) throws SQLException {
        String query = "SELECT * FROM items WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Item ID: " + rs.getInt("item_id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Price: " + rs.getDouble("price"));
                }
            }
        }
    }

    public void getAllItems() throws SQLException {
        String query = "SELECT * FROM items";
        try (PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Item ID: " + rs.getInt("item_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Price: " + rs.getDouble("price"));
                System.out.println("-----------");
            }
        }
    }

    public void updateItem(Item item) throws SQLException {
        String query = "UPDATE items SET name = ?, quantity = ?, price = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getQuantity());
            stmt.setDouble(3, item.getPrice());
            stmt.setInt(4, item.getItemId());
            stmt.executeUpdate();
        }
    }

    public void deleteItem(int itemId) throws SQLException {
        String query = "DELETE FROM items WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        }
    }
}
