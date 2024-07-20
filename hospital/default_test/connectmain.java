import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectmain {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         String url =  "jdbc:mysql://localhost:3306/hospital";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connectAdmin = DriverManager.getConnection(url, username, password);
        if (connectAdmin != null ) {
            System.out.println("Admin connection successfull");
        } else {
            System.out.println("Admin connection declined");
        }
    }
}
