import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/inventory";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            System.out.println("Admin connection successful");
        } else {
            System.out.println("Admin connection declined");
        }
        return connection;
    }
}
