import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    static Connection con;

    void getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/AudioPlayerDB";
            String username = "root";
            String pass = "";
            con = DriverManager.getConnection(url, username, pass);
            if (con == null) {
                System.out.println("Connection to server failed, try again later!");
            } else {
                System.out.println("Connnection success");
            }
        } catch (Exception e) {
            System.out.println("Exception has occured saying - " + e.getLocalizedMessage());
        }
    }
}
