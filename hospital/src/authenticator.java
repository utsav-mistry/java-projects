import java.sql.SQLException;
import java.util.Scanner;

public class authenticator {
    @SuppressWarnings("resource")
    public static boolean authenticate() throws ClassNotFoundException, SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username : ");
        String username = sc.next();
        System.out.print("Enter password : ");
        String pass = sc.next();
        if (username.equalsIgnoreCase(credentials.nameAdmin) && pass.equals(credentials.passAdmin)) {
            System.out.println("Connecting as Admin...");
            connect.doConnectAdmin();
            return true;
        } else if (username.equalsIgnoreCase(credentials.nameStaff) && pass.equals(credentials.passStaff)) {
            System.out.println("Connecting as staff...");
            connect.doConnectStaff();
            return true;
        } else if (username.equalsIgnoreCase(credentials.nameReception) && pass.equals(credentials.passReception)) {
            System.out.println("Connecting Reception");
            connect.doConnectReception();
            return true;
        } else {
            System.out.println("Oops! Wrong username or password");
            return false;
        }
    }
}
