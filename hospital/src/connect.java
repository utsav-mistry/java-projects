import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {
    static Connection connectAdmin, connectStaff, connectReception;
    static String type;
    public static void doConnectAdmin() throws SQLException, ClassNotFoundException{
        String url =  "jdbc:mysql://localhost:3306/hospital";
        String username = "admin";
        String password = "hospital.admin";
        type = "admin";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connectAdmin = DriverManager.getConnection(url, username, password);
        if (connectAdmin != null ) {
            System.out.println("Admin connection successfull");
        } else {
            System.out.println("Admin connection declined");
        }
    }

    public static void doConnectStaff() throws SQLException, ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/hospital";
        String username = "staff";
        String password = "hospital.staff";
        type = "staff";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connectStaff = DriverManager.getConnection(url, username, password);
        if (connectStaff != null) {
            System.out.println("Staff connection successful\nWith limited previleges");
        } else {
            System.out.println("Staff connection failed");
        }
    }

    public static void doConnectReception() throws SQLException, ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/hospital";
        String username = "reception";
        String password = "hospital.reception";
        type = "reception";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connectReception = DriverManager.getConnection(url, username, password);
        if (connectReception != null) {
            System.out.println("Reception connection successful\nWith limited previleges");
        } else {
            System.out.println("Reception connection failed");
        }
    }
}
