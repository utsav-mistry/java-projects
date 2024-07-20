import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class select {
    static Statement s;
    static PreparedStatement pst;

    //table department
    public static void doSelect_department() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `department`";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Select, error in connection");
            return;
        }

        System.out.println("+---------+--------------------------------+");
        System.out.println("|   ID    |            NAME                |");
        System.out.println("+---------+--------------------------------+");
        ResultSet r = pst.executeQuery();
        while (r.next()) {
            int id = r.getInt("dep_id");
            String name = r.getString("dep_name");
            System.out.printf("| %7d | %-30s |\n", id, name);
        }
        System.out.println("+---------+--------------------------------+");
    }            
    //table employee
    public static void doSelect_employee() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `employee`";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Select, error in connection");
            return;
        }
        System.out.println("+----+---------+--------------+-------------+--------------------------------+---------------------------------+");
        System.out.println("| ID |Dept. ID |  First Name  |  Last Name  |             Email              |            ADDRESS              |");
        System.out.println("+----+---------+--------------+-------------+--------------------------------+---------------------------------+");
        ResultSet r = pst.executeQuery();
        while (r.next()) {
            int id = r.getInt("emp_id");
            int depId = r.getInt("dep_id");
            String firstName = r.getString("f_name");
            String lastName = r.getString("l_name");
            String email = r.getString("emp_mail");
            String address = r.getString("address");
            System.out.printf("| %2d | %7d | %-12s | %-11s | %-30s | %-31s |\n", id, depId, firstName, lastName, email, address);
        }
        System.out.println("+----+---------+--------------+-------------+--------------------------------+---------------------------------+");
    }

    //table icu
    public static void doSelect_icu() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `icu`";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Select, error in connection");
            return;
        }

        System.out.println("+--------+-------------------+-------+");
        System.out.println("| ICU ID |       Type        | Floor |");
        System.out.println("+--------+-------------------+-------+");
        
        ResultSet r = pst.executeQuery();
        while (r.next()) {
            int icuId = r.getInt("icu_id");
            String type = r.getString("type");
            int floor = r.getInt("floor");

            // Print each row with formatted columns
            System.out.printf("| %6d | %-17s | %5d |\n", icuId, type, floor);
        }
        System.out.println("+--------+-------------------+-------+");
     
    }

    //table ot
    public static void doSelect_ot() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `ot`";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Select, error in connection");
            return;
        }

        ResultSet r = pst.executeQuery();
        System.out.println("+--------+-------------+------------+--------+--------+------------+");
        System.out.println("| OT ID  | Patient ID  | Surgery ID | Emp ID | Med ID |    Date    |");
        System.out.println("+--------+-------------+------------+--------+--------+------------+");
        while (r.next()) {
            int optId = r.getInt("opt_id");
            int patientId = r.getInt("patient_id");
            int surgeryId = r.getInt("surgery_id");
            int empId = r.getInt("emp_id");
            int medId = r.getInt("med_id");
            Date date = r.getDate("date");

            // Print each row with formatted columns
            System.out.printf("| %6d | %11d | %10d | %6d | %6d | %tF |\n", optId, patientId, surgeryId, empId, medId, date); 
        }
        System.out.println("+--------+-------------+------------+--------+--------+------------+");
     
    }

    //table patient
    public static void doSelect_patient() throws SQLException, ClassNotFoundException {
        String sql = "Select * FROM `patient`";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Select, error in connection");
            return;
        }
        
        ResultSet r = pst.executeQuery();
        System.out.println("+----+-----------+-----------+------------------------------------+-----+-------+------------+");
        System.out.println("| ID |  F_NAME   |  L_NAME   |              ADDRESS               | AGE | ICU   | BLOOD GROUP|");
        System.out.println("+----+-----------+-----------+------------------------------------+-----+-------+------------+");

        while (r.next()) {
            int id = r.getInt("patient_id");
            String fName = r.getString("f_name");
            String lName = r.getString("l_name");
            String address = r.getString("address");
            int age = r.getInt("age");
            int icuId = r.getInt("icu_id");
            String bloodGroup = r.getString("bloodgroup");

            System.out.printf("| %-3d| %-10s| %-10s| %-35s| %-3d | %-5d | %-10s |\n", id, fName, lName, address, age, icuId, bloodGroup);
        }     
        System.out.println("+----+-----------+-----------+------------------------------------+-----+-------+------------+");    

    }
    //table pharmacy
    public static void doSelect_pharmacy() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `pharmacy`";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Select, error in connection");
            return;
        }
       
        
        // Execute query and get ResultSet
        ResultSet r = pst.executeQuery();

        // Print table header
        System.out.println("+----------+----------------------+------------------------------------------+-------------------------------------+");
        System.out.println("| Med ID   | Medication Name      |              Manufacturer                |           Abbreviated Usage         |");
        System.out.println("+----------+----------------------+------------------------------------------+-------------------------------------+");
        while (r.next()) {
            int medId = r.getInt("med_id");
            String medName = r.getString("med_name");
            String medManufacturer = r.getString("med_manufacturer");
            String abbreviatedUsage = r.getString("abbreviated_usage");

            System.out.printf("| %8d | %-20s | %-40s | %-35s |\n", medId, medName, medManufacturer, abbreviatedUsage);
        }
        System.out.println("+----------+----------------------+------------------------------------------+-------------------------------------+");
    }

    //table surgery
    public static void doSelect_surgery() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `surgery`";
        
        if (connect.type.equals("admin")) {
            s = connect.connectAdmin.createStatement();
            pst = connect.connectAdmin.prepareStatement(sql);
        } else if (connect.type.equals("staff")) {
            s = connect.connectStaff.createStatement();
            pst = connect.connectStaff.prepareStatement(sql);
        } else {
            System.out.println("Can't Select, error in connection");
            return;
        }
        ResultSet r = pst.executeQuery();

        System.out.println("+------------+-------------------------+--------+--------+");
        System.out.println("| Surgery ID |      Surgery Name       | Emp ID | Med ID |");
        System.out.println("+------------+-------------------------+--------+--------+");

        while (r.next()) {
            int surgeryId = r.getInt("surgery_id");
            String surgeryName = r.getString("surgery_name");
            int empId = r.getInt("emp_id");
            int medId = r.getInt("med_id");
            System.out.printf("| %10d | %-23s | %6d | %6d |\n", surgeryId, surgeryName, empId, medId);
        }
        System.out.println("+------------+-------------------------+--------+--------+");  
    }

    //super implementation
    public static void implementSelect() throws SQLException, ClassNotFoundException {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag != false) {
            System.out.println("Tables to View\n1.Department\t2.Employee\t3.ICU\t4.OT\t5.Patient\t6.Pharmacy\t7.Surgery\t8.None, Exit");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    doSelect_department();
                    break;
                case 2:
                    doSelect_employee();
                    break;
                case 3:
                    doSelect_icu();
                    break;
                case 4:
                    doSelect_ot();
                    break;
                case 5:
                    doSelect_patient();
                    break;
                case 6:
                    doSelect_pharmacy();
                    break;
                case 7:
                    doSelect_surgery();
                    break;
                case 8:
                    flag = false;
                    System.out.println("Select operation completed successfully and exited.");
                    break;
                default:
                    System.out.println("Invalid choice entered");
                    break;
            }
        }
    }
}
