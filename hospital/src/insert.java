import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDate;

public class insert {
    static Statement s;
    static PreparedStatement pst;

    //table department
    @SuppressWarnings("resource")
    public static void doInsert_department() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String sql = "INSERT INTO `department` (dep_name) VALUES (?)";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Insert, error in connection");
            return;
        }

        System.out.println("Department Name: ");
        String deptName = sc.next();
       
        pst.setString(2, deptName);
        
        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Insert failed");
        } else {
            System.out.println("Insert completed successfully.");
        }        
    }
    //table employee
    @SuppressWarnings("resource")
    public static void doInsert_employee() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String sql = "INSERT INTO `employee` (dep_id, f_name, l_name, emp_mail, address) VALUES (?, ?, ?, ?, ?)";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Insert, error in connection");
            return;
        }

        System.out.println("Department ID: ");
        int id = sc.nextInt();
        System.out.println("First Name: ");
        String fname = sc.next();
        System.out.println("Last Name: ");
        String lname = sc.next();
        System.out.println("Generating mail ID for new employee...");
        String mail = fname + "." + lname + "@example.com";
        System.out.println("Your mail ID is \n"+mail);
        System.out.println("Address: ");
        String address = sc.next();
        pst.setInt(2, id);
        pst.setString(3, fname);
        pst.setString(4, lname);
        pst.setString(5, mail);
        pst.setString(6, address);
        
        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Insert failed");
        } else {
            System.out.println("Insert completed successfully.");
        }        
    }

    //table icu
    @SuppressWarnings("resource")
    public static void doInsert_icu() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String sql = "INSERT INTO `icu` (type, floor) VALUES (?, ?)";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Insert, error in connection");
            return;
        }

        System.out.println("Type: ");
        String type = sc.nextLine();
        System.out.println("Floor(4-7): ");
        String floor = sc.nextLine();

        pst.setString(2, type);
        pst.setString(3, floor);
        
        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Insert failed");
        } else {
            System.out.println("Insert completed successfully.");
        }        
    }

    //table ot
    @SuppressWarnings("resource")
    public static void doInsert_ot() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String sql = "INSERT INTO `ot` (patient_id, surgery_id, emp_id, med_id, date) VALUES (?, ?, ?, ?, ?)";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Insert, error in connection");
            return;
        }

        System.out.println("Patient ID: ");
        int p_id = sc.nextInt();
        System.out.println("Surgery ID: ");
        int surg_id = sc.nextInt();
        System.out.println("Employee ID: ");
        int e_id = sc.nextInt();
        System.out.println("Medicine ID: ");
        int m_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Date: ");
        LocalDate ld = LocalDate.now();
        String date = ld.toString();
        pst.setInt(2, p_id);
        pst.setInt(3, surg_id);
        pst.setInt(4, e_id);
        pst.setInt(5, m_id);
        pst.setString(6, date);
        
        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Insert failed");
        } else {
            System.out.println("Insert completed successfully.");
        }        
    }

    //table patient
    @SuppressWarnings("resource")
    public static void doInsert_patient() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String sql = "INSERT INTO `patient` (f_name, l_name, address, age, bloodgroup) VALUES (?, ?, ?, ?, ?)";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Insert, error in connection");
            return;
        }

        System.out.println("First Name: ");
        String fname = sc.nextLine();
        System.out.println("Last Name: ");
        String lname = sc.next();
        System.out.println("Address: ");
        String address = sc.next();
        System.out.println("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Bloodgroup: ");
        String bloodgroup = sc.nextLine();
        pst.setString(1, fname);
        pst.setString(2, lname);
        pst.setString(3, address);
        pst.setInt(4, age);
        pst.setString(5, bloodgroup);
        
        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Insert failed");
        } else {
            System.out.println("Insert completed successfully.");
        }        
    }

    //table pharmacy
    @SuppressWarnings("resource")
    public static void doInsert_pharmacy() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String sql = "INSERT INTO `patient` (med_name, med_manufacturer, abbreviated_usage) VALUES (?, ?, ?, ?)";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Insert, error in connection");
            return;
        }

        System.out.println("Medicine Name: ");
        String m_name = sc.nextLine();
        System.out.println("medicine Manufacturer: ");
        String m_man = sc.next();
        System.out.println("Abbreviated Usage: ");
        String a_u = sc.next();
       
        pst.setString(2, m_name);
        pst.setString(3, m_man);
        pst.setString(4, a_u);
        
        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Insert failed");
        } else {
            System.out.println("Insert completed successfully.");
        }        
    }

    //table surgery
    @SuppressWarnings("resource")
    public static void doInsert_surgery() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String sql = "INSERT INTO `patient` (surgery_name, emp_id, med_id, ) VALUES (?, ?, ?)";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Insert, error in connection");
            return;
        }

        System.out.println("Surgery Name: ");
        String s_name = sc.nextLine();
        System.out.println("Employee ID: ");
        int e_id = sc.nextInt();
        System.out.println("Medicine ID: ");
        String m_id = sc.nextLine();        

        pst.setString(2, s_name);
        pst.setInt(3, e_id);
        pst.setString(4, m_id);
        
        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Insert failed");
        } else {
            System.out.println("Insert completed successfully.");
        }        
    }

    //super implementation
    public static void implementInsert() throws SQLException, ClassNotFoundException {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag != false) {
            System.out.println("Tables to insert\n1.Department\t2.Employee\t3.ICU\t4.OT\t5.Patient\t6.Pharmacy\t7.Surgery\t8.None, Exit");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    doInsert_department();
                    break;
                case 2:
                    doInsert_employee();
                    break;
                case 3:
                    doInsert_icu();
                    break;
                case 4:
                    doInsert_ot();
                    break;
                case 5:
                    doInsert_patient();
                    break;
                case 6:
                    doInsert_pharmacy();
                    break;
                case 7:
                    doInsert_surgery();
                    break;
                case 8:
                    flag = false;
                    System.out.println("Insert operation completed successfully and exited.");
                    break;
                default:
                    System.out.println("Invalid choice entered");
                    break;
            }
        }
    }
}
