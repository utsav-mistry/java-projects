import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class update {
    static Statement s;
    static PreparedStatement pst;
    static PreparedStatement pst1;

    public static void doUpdate_department() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `department` SET `dep_name` = ? WHERE `dep_id` = ?";
        String sql1 = "SELECT * FROM `department` WHERE `dep_id` = ?";
        final int did;
        if (connect.type.equals("admin")) {
            pst = connect.connectAdmin.prepareStatement(sql);
            pst1 = connect.connectAdmin.prepareStatement(sql1);
        } else if (connect.type.equals("staff")) {
            pst = connect.connectStaff.prepareStatement(sql);
            pst1 = connect.connectStaff.prepareStatement(sql1);
        } else {
            System.out.println("Can't Update, error in connection");
            return;
        }
    
        select.doSelect_department();
    
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter department ID to update its name:");
        did = sc.nextInt();
        sc.nextLine(); 
    
        pst1.setInt(1, did);
        ResultSet r = pst1.executeQuery();
    
        System.out.println("+---------+--------------------------------+");
        System.out.println("|   ID    |            NAME                |");
        System.out.println("+---------+--------------------------------+");
    
        boolean found = false;
    
        while (r.next()) {
            int id = r.getInt("dep_id");
            String name = r.getString("dep_name");
    
            if (id == did) {
                found = true;
                System.out.printf("| %7d | %-30s |\n", id, name);
                break;
            }
        }
    
        System.out.println("+---------+--------------------------------+");
    
        if (!found) {
            System.out.println("Department ID not found.");
            return;
        }
    
        System.out.println("Enter the new department name:");
        String newName = sc.nextLine();
    
        pst.setString(1, newName);
        pst.setInt(2, did);
    
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Department name updated successfully.");
        } else {
            System.out.println("Failed to update department name.");
            return;
        }
    
        r = pst1.executeQuery(); 
        System.out.println("+---------+--------------------------------+");
        System.out.println("|   ID    |            NAME                |");
        System.out.println("+---------+--------------------------------+");
    
        while (r.next()) {
            int id = r.getInt("dep_id");
            String name = r.getString("dep_name");
    
            if (id == did) {
                System.out.printf("| %7d | %-30s |\n", id, name);
                break;
            }
        }
    
        System.out.println("+---------+--------------------------------+");
    }
        
          
    //table employee
    public static void doUpdate_employee() throws SQLException, ClassNotFoundException {
        String sqlTemplate = "UPDATE `employee` SET %s = ? WHERE `emp_id` = ?";
        String sql1 = "SELECT * FROM `employee` WHERE `emp_id` = ?";
        final int eid;
    
        if (connect.type.equals("admin")) {
            pst1 = connect.connectAdmin.prepareStatement(sql1);
        } else if (connect.type.equals("staff")) {
            pst1 = connect.connectStaff.prepareStatement(sql1);
        } else {
            System.out.println("Can't Update, error in connection");
            return;
        }
    
        select.doSelect_employee();
    
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee ID to update their details:");
        eid = sc.nextInt();
        sc.nextLine();
    
        pst1.setInt(1, eid);
        ResultSet r = pst1.executeQuery();
    
        System.out.println("+----+---------+--------------+-------------+------------------------------+-------------------------------+");
        System.out.println("| ID | Dep ID  | First Name   | Last Name   |           Email              |             Address           |");
        System.out.println("+----+---------+--------------+-------------+------------------------------+-------------------------------+");
    
        boolean found = false;
    
        while (r.next()) {
            int id = r.getInt("emp_id");
            int depId = r.getInt("dep_id");
            String firstName = r.getString("f_name");
            String lastName = r.getString("l_name");
            String email = r.getString("emp_mail");
            String address = r.getString("address");
    
            if (id == eid) {
                found = true;
                System.out.printf("| %2d | %7d | %-12s | %-11s | %-30s | %-31s |\n", id, depId, firstName, lastName, email, address);
                break;
            }
        }
    
        System.out.println("+----+---------+--------------+-------------+------------------------------+-------------------------------+");
    
        if (!found) {
            System.out.println("Employee ID not found.");
            return;
        }
    
        System.out.println("Which column do you want to update?");
        System.out.println("1. dep_id");
        System.out.println("2. f_name");
        System.out.println("3. l_name");
        System.out.println("4. emp_mail");
        System.out.println("5. address");
        System.out.println("6. Cancel Update");
        int choice = sc.nextInt();
        sc.nextLine();
    
        String columnToUpdate = null;
        switch (choice) {
            case 1:
                columnToUpdate = "dep_id";
                break;
            case 2:
                columnToUpdate = "f_name";
                break;
            case 3:
                columnToUpdate = "l_name";
                break;
            case 4:
                columnToUpdate = "emp_mail";
                break;
            case 5:
                columnToUpdate = "address";
                break;
            case 6:
                System.out.println("Update cancelled");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        System.out.println("Enter the new value for " + columnToUpdate + ":");
        String newValue = sc.nextLine();
    
        String sql = String.format(sqlTemplate, columnToUpdate);
        PreparedStatement updatePst;
        if (connect.type.equals("admin")) {
            updatePst = connect.connectAdmin.prepareStatement(sql);
        } else {
            updatePst = connect.connectStaff.prepareStatement(sql);
        }
        updatePst.setString(1, newValue);
        updatePst.setInt(2, eid);
    
        int rowsAffected = updatePst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(columnToUpdate + " updated successfully.");
        } else {
            System.out.println("Failed to update " + columnToUpdate + ".");
            return;
        }
    
        r = pst1.executeQuery(); 
        System.out.println("+----+---------+--------------+-------------+------------------------------+-------------------------------+");
        System.out.println("| ID | Dep ID  | First Name   | Last Name   |           Email              |            Address            |");
        System.out.println("+----+---------+--------------+-------------+------------------------------+-------------------------------+");
    
        while (r.next()) {
            int id = r.getInt("emp_id");
            int depId = r.getInt("dep_id");
            String firstName = r.getString("f_name");
            String lastName = r.getString("l_name");
            String email = r.getString("emp_mail");
            String address = r.getString("address");
    
            if (id == eid) {
                System.out.printf("| %2d | %7d | %-12s | %-11s | %-30s | %-31s |\n", id, depId, firstName, lastName, email, address);
                break;
            }
        }
    
        System.out.println("+----+---------+--------------+-------------+------------------------------+-------------------------------+");
    }
    
    

    //table icu
    public static void doUpdate_icu() throws SQLException, ClassNotFoundException {
        String sqlTemplate = "UPDATE `icu` SET %s = ? WHERE `icu_id` = ?";
        String sql1 = "SELECT * FROM `icu` WHERE `icu_id` = ?";
        final int icuId;
    
        if (connect.type.equals("admin")) {
            pst1 = connect.connectAdmin.prepareStatement(sql1);
        } else if (connect.type.equals("staff")) {
            pst1 = connect.connectStaff.prepareStatement(sql1);
        } else {
            System.out.println("Can't Update, error in connection");
            return;
        }
    
        select.doSelect_icu();
    
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ICU ID to update its details:");
        icuId = sc.nextInt();
        sc.nextLine();
    
        pst1.setInt(1, icuId);
        ResultSet r = pst1.executeQuery();
    
        System.out.println("+--------+-------------------+-------+");
        System.out.println("| ICU ID |        Type       | Floor |");
        System.out.println("+--------+-------------------+-------+");
    
        boolean found = false;
    
        while (r.next()) {
            int id = r.getInt("icu_id");
            String type = r.getString("type");
            int floor = r.getInt("floor");
    
            if (id == icuId) {
                found = true;
                System.out.printf("| %6d | %-17s | %5d |\n", id, type, floor);
                break;
            }
        }
    
        System.out.println("+--------+-------------------+-------+");
    
        if (!found) {
            System.out.println("ICU ID not found.");
            return;
        }
    
        System.out.println("Which column do you want to update?");
        System.out.println("1. type");
        System.out.println("2. floor");
        System.out.println("3. Cancel Update");
        int choice = sc.nextInt();
        sc.nextLine(); 

        String columnToUpdate = null;
        switch (choice) {
            case 1:
                columnToUpdate = "type";
                break;
            case 2:
                columnToUpdate = "floor";
                break;
            case 3:
                System.out.println("Update cancelled");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        System.out.println("Enter the new value for " + columnToUpdate + ":");
        String newValue = sc.nextLine();
    
        String sql = String.format(sqlTemplate, columnToUpdate);
        PreparedStatement updatePst;
        if (connect.type.equals("admin")) {
            updatePst = connect.connectAdmin.prepareStatement(sql);
        } else {
            updatePst = connect.connectStaff.prepareStatement(sql);
        }
        updatePst.setString(1, newValue);
        updatePst.setInt(2, icuId);
    
        int rowsAffected = updatePst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(columnToUpdate + " updated successfully.");
        } else {
            System.out.println("Failed to update " + columnToUpdate + ".");
            return;
        }
    
        r = pst1.executeQuery(); 
        System.out.println("+--------+-------------------+-------+");
        System.out.println("| ICU ID |        Type       | Floor |");
        System.out.println("+--------+-------------------+-------+");
    
        while (r.next()) {
            int id = r.getInt("icu_id");
            String type = r.getString("type");
            int floor = r.getInt("floor");
    
            if (id == icuId) {
                System.out.printf("| %6d | %-17s | %5d |\n", id, type, floor);
                break;
            }
        }
    
        System.out.println("+--------+-------------------+-------+");
    }
    

    //table ot
    public static void doUpdate_ot() throws SQLException, ClassNotFoundException {
        String sqlTemplate = "UPDATE `ot` SET %s = ? WHERE `opt_id` = ?";
        String sql1 = "SELECT * FROM `ot` WHERE `opt_id` = ?";
        final int optId;
    
        if (connect.type.equals("admin")) {
            pst1 = connect.connectAdmin.prepareStatement(sql1);
        } else if (connect.type.equals("staff")) {
            pst1 = connect.connectStaff.prepareStatement(sql1);
        } else {
            System.out.println("Can't Update, error in connection");
            return;
        }
    
        select.doSelect_ot(); 
    
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter OT ID to update its details:");
        optId = sc.nextInt();
        sc.nextLine();
    
        pst1.setInt(1, optId);
        ResultSet r = pst1.executeQuery();
    
        System.out.println("+--------+------------+------------+-------+-------+------------+");
        System.out.println("| OT ID  | Patient ID | Surgery ID | Emp ID| Med ID |    Date    |");
        System.out.println("+--------+------------+------------+-------+-------+------------+");
    
        boolean found = false;
    
        while (r.next()) {
            int id = r.getInt("opt_id");
            int patientId = r.getInt("patient_id");
            int surgeryId = r.getInt("surgery_id");
            int empId = r.getInt("emp_id");
            int medId = r.getInt("med_id");
            Date date = r.getDate("date");
    
            if (id == optId) {
                found = true;
                System.out.printf("| %6d | %11d | %10d | %6d | %6d | %tF |\n", id, patientId, surgeryId, empId, medId, date);
                break;
            }
        }
    
        System.out.println("+--------+------------+------------+-------+-------+------------+");
    
        if (!found) {
            System.out.println("OT ID not found.");
            return;
        }
    
        System.out.println("Which column do you want to update?");
        System.out.println("1. patient_id");
        System.out.println("2. surgery_id");
        System.out.println("3. emp_id");
        System.out.println("4. med_id");
        System.out.println("5. date");
        System.out.println("6. Cancel Update");
        int choice = sc.nextInt();
        sc.nextLine();
    
        String columnToUpdate = null;
        switch (choice) {
            case 1:
                columnToUpdate = "patient_id";
                break;
            case 2:
                columnToUpdate = "surgery_id";
                break;
            case 3:
                columnToUpdate = "emp_id";
                break;
            case 4:
                columnToUpdate = "med_id";
                break;
            case 5:
                columnToUpdate = "date";
                break;
            case 6:
                System.out.println("Update cancelled");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        System.out.println("Enter the new value for " + columnToUpdate + ":");
        String newValue = sc.nextLine();
    
        String sql = String.format(sqlTemplate, columnToUpdate);
        PreparedStatement updatePst;
        if (connect.type.equals("admin")) {
            updatePst = connect.connectAdmin.prepareStatement(sql);
        } else {
            updatePst = connect.connectStaff.prepareStatement(sql);
        }
        if (columnToUpdate.equals("date")) {
            Date newDate = Date.valueOf(newValue);
            updatePst.setDate(1, newDate);
        } else {
            updatePst.setString(1, newValue);
        }
        updatePst.setInt(2, optId);
    
        int rowsAffected = updatePst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(columnToUpdate + " updated successfully.");
        } else {
            System.out.println("Failed to update " + columnToUpdate + ".");
            return;
        }
    
        r = pst1.executeQuery();
        System.out.println("+--------+------------+------------+-------+-------+------------+");
        System.out.println("| OT ID  | Patient ID | Surgery ID | Emp ID| Med ID |    Date    |");
        System.out.println("+--------+------------+------------+-------+-------+------------+");
    
        while (r.next()) {
            int id = r.getInt("opt_id");
            int patientId = r.getInt("patient_id");
            int surgeryId = r.getInt("surgery_id");
            int empId = r.getInt("emp_id");
            int medId = r.getInt("med_id");
            Date date = r.getDate("date");
    
            if (id == optId) {
                System.out.printf("| %6d | %11d | %10d | %6d | %6d | %tF |\n", id, patientId, surgeryId, empId, medId, date);
                break;
            }
        }
    
        System.out.println("+--------+------------+------------+-------+-------+------------+");
    }
    

    //table patient
    public static void doUpdate_patient() throws SQLException, ClassNotFoundException {
        String sqlTemplate = "UPDATE `patient` SET %s = ? WHERE `patient_id` = ?";
        String sql1 = "SELECT * FROM `patient` WHERE `patient_id` = ?";
        final int patientId;
    
        if (connect.type.equals("admin")) {
            pst1 = connect.connectAdmin.prepareStatement(sql1);
        } else if (connect.type.equals("staff")) {
            pst1 = connect.connectStaff.prepareStatement(sql1);
        } else {
            System.out.println("Can't Update, error in connection");
            return;
        }
    
        select.doSelect_patient();
    
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter patient ID to update their details:");
        patientId = sc.nextInt();
        sc.nextLine();
    
        pst1.setInt(1, patientId);
        ResultSet r = pst1.executeQuery();
    
        System.out.println("+---------+--------------+-------------+--------------------------------+-------+--------+--------------+");
        System.out.println("| Patient |  First Name  |  Last Name  |              Address           |  Age  | ICU ID |  Blood Group |");
        System.out.println("+---------+--------------+-------------+--------------------------------+-------+--------+--------------+");
    
        boolean found = false;
    
        while (r.next()) {
            int id = r.getInt("patient_id");
            String fName = r.getString("f_name");
            String lName = r.getString("l_name");
            String address = r.getString("address");
            int age = r.getInt("age");
            int icuId = r.getInt("icu_id");
            String bloodGroup = r.getString("bloodgroup");
    
            if (id == patientId) {
                found = true;
                System.out.printf("| %8d | %-12s | %-11s | %-30s | %3d | %6d | %-13s |\n", id, fName, lName, address, age, icuId, bloodGroup);
                break;
            }
        }
    
        System.out.println("+---------+--------------+-------------+--------------------------------+-------+--------+--------------+");
    
        if (!found) {
            System.out.println("Patient ID not found.");
            return;
        }
    
        System.out.println("Which column do you want to update?");
        System.out.println("1. f_name");
        System.out.println("2. l_name");
        System.out.println("3. address");
        System.out.println("4. age");
        System.out.println("5. icu_id");
        System.out.println("6. bloodgroup");
        System.out.println("7. Cancel Update");
        int choice = sc.nextInt();
        sc.nextLine();
    
        String columnToUpdate = null;
        switch (choice) {
            case 1:
                columnToUpdate = "f_name";
                break;
            case 2:
                columnToUpdate = "l_name";
                break;
            case 3:
                columnToUpdate = "address";
                break;
            case 4:
                columnToUpdate = "age";
                break;
            case 5:
                columnToUpdate = "icu_id";
                break;
            case 6:
                columnToUpdate = "bloodgroup";
                break;
            case 7:
                System.out.println("Update cancelled");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        System.out.println("Enter the new value for " + columnToUpdate + ":");
        String newValue = sc.nextLine();
    
        String sql = String.format(sqlTemplate, columnToUpdate);
        PreparedStatement updatePst;
        if (connect.type.equals("admin")) {
            updatePst = connect.connectAdmin.prepareStatement(sql);
        } else {
            updatePst = connect.connectStaff.prepareStatement(sql);
        }
        updatePst.setString(1, newValue);
        updatePst.setInt(2, patientId);
    
        int rowsAffected = updatePst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(columnToUpdate + " updated successfully.");
        } else {
            System.out.println("Failed to update " + columnToUpdate + ".");
            return;
        }
    
        r = pst1.executeQuery();
        System.out.println("+---------+--------------+-------------+--------------------------------+-------+--------+--------------+");
        System.out.println("| Patient |  First Name  |  Last Name  |              Address           |  Age  | ICU ID |  Blood Group |");
        System.out.println("+---------+--------------+-------------+--------------------------------+-------+--------+--------------+");
    
        while (r.next()) {
            int id = r.getInt("patient_id");
            String fName = r.getString("f_name");
            String lName = r.getString("l_name");
            String address = r.getString("address");
            int age = r.getInt("age");
            int icuId = r.getInt("icu_id");
            String bloodGroup = r.getString("bloodgroup");
    
            if (id == patientId) {
                System.out.printf("| %8d | %-12s | %-11s | %-30s | %3d | %6d | %-13s |\n", id, fName, lName, address, age, icuId, bloodGroup);
                break;
            }
        }
    
        System.out.println("+---------+--------------+-------------+--------------------------------+-------+--------+--------------+");
    }
    
    //table pharmacy
    public static void doUpdate_pharmacy() throws SQLException, ClassNotFoundException {
        String sqlTemplate = "UPDATE `pharmacy` SET %s = ? WHERE `med_id` = ?";
        String sql1 = "SELECT * FROM `pharmacy` WHERE `med_id` = ?";
        final int medId;
    
        if (connect.type.equals("admin")) {
            pst1 = connect.connectAdmin.prepareStatement(sql1);
        } else if (connect.type.equals("staff")) {
            pst1 = connect.connectStaff.prepareStatement(sql1);
        } else {
            System.out.println("Can't Update, error in connection");
            return;
        }
    
        select.doSelect_pharmacy();
    
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter medication ID to update its details:");
        medId = sc.nextInt();
        sc.nextLine();
    
        pst1.setInt(1, medId);
        ResultSet r = pst1.executeQuery();
    
        System.out.println("+----------+-----------------------+---------------------------+--------------------------+");
        System.out.println("|  Med ID  |        Med Name       |     Med Manufacturer      |  Abbreviated Usage       |");
        System.out.println("+----------+-----------------------+---------------------------+--------------------------+");
    
        boolean found = false;
    
        while (r.next()) {
            int id = r.getInt("med_id");
            String medName = r.getString("med_name");
            String medManufacturer = r.getString("med_manufacturer");
            String abbreviatedUsage = r.getString("abbreviated_usage");
    
            if (id == medId) {
                found = true;
                System.out.printf("| %8d | %-23s | %-25s | %-24s |\n", id, medName, medManufacturer, abbreviatedUsage);
                break;
            }
        }
    
        System.out.println("+----------+-----------------------+---------------------------+--------------------------+");
    
        if (!found) {
            System.out.println("Medication ID not found.");
            return;
        }
    
        System.out.println("Which column do you want to update?");
        System.out.println("1. med_name");
        System.out.println("2. med_manufacturer");
        System.out.println("3. abbreviated_usage");
        System.out.println("4. Cancel Update");
        int choice = sc.nextInt();
        sc.nextLine();
    
        String columnToUpdate = null;
        switch (choice) {
            case 1:
                columnToUpdate = "med_name";
                break;
            case 2:
                columnToUpdate = "med_manufacturer";
                break;
            case 3:
                columnToUpdate = "abbreviated_usage";
                break;
            case 4:
                System.out.println("Update cancelled");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        System.out.println("Enter the new value for " + columnToUpdate + ":");
        String newValue = sc.nextLine();
    
        String sql = String.format(sqlTemplate, columnToUpdate);
        PreparedStatement updatePst;
        if (connect.type.equals("admin")) {
            updatePst = connect.connectAdmin.prepareStatement(sql);
        } else {
            updatePst = connect.connectStaff.prepareStatement(sql);
        }
        updatePst.setString(1, newValue);
        updatePst.setInt(2, medId);
    
        int rowsAffected = updatePst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(columnToUpdate + " updated successfully.");
        } else {
            System.out.println("Failed to update " + columnToUpdate + ".");
            return;
        }
    
        r = pst1.executeQuery();
        System.out.println("+----------+-----------------------+---------------------------+--------------------------+");
        System.out.println("|  Med ID  |        Med Name       |     Med Manufacturer      |  Abbreviated Usage       |");
        System.out.println("+----------+-----------------------+---------------------------+--------------------------+");
    
        while (r.next()) {
            int id = r.getInt("med_id");
            String medName = r.getString("med_name");
            String medManufacturer = r.getString("med_manufacturer");
            String abbreviatedUsage = r.getString("abbreviated_usage");
    
            if (id == medId) {
                System.out.printf("| %8d | %-23s | %-25s | %-24s |\n", id, medName, medManufacturer, abbreviatedUsage);
                break;
            }
        }
    
        System.out.println("+----------+-----------------------+---------------------------+--------------------------+");
    }
    

    //table surgery
    public static void doUpdate_surgery() throws SQLException, ClassNotFoundException {
        String sqlTemplate = "UPDATE `surgery` SET %s = ? WHERE `surgery_id` = ?";
        String sql1 = "SELECT * FROM `surgery` WHERE `surgery_id` = ?";
        final int surgeryId;
    
        if (connect.type.equals("admin")) {
            pst1 = connect.connectAdmin.prepareStatement(sql1);
        } else if (connect.type.equals("staff")) {
            pst1 = connect.connectStaff.prepareStatement(sql1);
        } else {
            System.out.println("Can't Update, error in connection");
            return;
        }
    
        select.doSelect_surgery();
    
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter surgery ID to update its details:");
        surgeryId = sc.nextInt();
        sc.nextLine();
    
        pst1.setInt(1, surgeryId);
        ResultSet r = pst1.executeQuery();
    
        System.out.println("+------------+-------------------------+--------+--------+");
        System.out.println("| Surgery ID |      Surgery Name       | Emp ID | Med ID |");
        System.out.println("+------------+-------------------------+--------+--------+");
    
        boolean found = false;
    
        while (r.next()) {
            int id = r.getInt("surgery_id");
            String surgeryName = r.getString("surgery_name");
            int empId = r.getInt("emp_id");
            int medId = r.getInt("med_id");
    
            if (id == surgeryId) {
                found = true;
                System.out.printf("| %10d | %-23s | %6d | %6d |\n", id, surgeryName, empId, medId);
                break;
            }
        }
    
        System.out.println("+------------+-------------------------+--------+--------+");
    
        if (!found) {
            System.out.println("Surgery ID not found.");
            return;
        }
    
        System.out.println("Which column do you want to update?");
        System.out.println("1. surgery_name");
        System.out.println("2. emp_id");
        System.out.println("3. med_id");
        System.out.println("4. Cancel Update");
        int choice = sc.nextInt();
        sc.nextLine();
    
        String columnToUpdate = null;
        switch (choice) {
            case 1:
                columnToUpdate = "surgery_name";
                break;
            case 2:
                columnToUpdate = "emp_id";
                break;
            case 3:
                columnToUpdate = "med_id";
                break;
            case 4:
                System.out.println("Update cancelled");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        System.out.println("Enter the new value for " + columnToUpdate + ":");
        String newValue = sc.nextLine();
    
        String sql = String.format(sqlTemplate, columnToUpdate);
        PreparedStatement updatePst;
        if (connect.type.equals("admin")) {
            updatePst = connect.connectAdmin.prepareStatement(sql);
        } else {
            updatePst = connect.connectStaff.prepareStatement(sql);
        }
        updatePst.setString(1, newValue);
        updatePst.setInt(2, surgeryId);
    
        int rowsAffected = updatePst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(columnToUpdate + " updated successfully.");
        } else {
            System.out.println("Failed to update " + columnToUpdate + ".");
            return;
        }
    
        r = pst1.executeQuery();
        System.out.println("+------------+-------------------------+--------+--------+");
        System.out.println("| Surgery ID |      Surgery Name       | Emp ID | Med ID |");
        System.out.println("+------------+-------------------------+--------+--------+");
    
        while (r.next()) {
            int id = r.getInt("surgery_id");
            String surgeryName = r.getString("surgery_name");
            int empId = r.getInt("emp_id");
            int medId = r.getInt("med_id");
    
            if (id == surgeryId) {
                System.out.printf("| %10d | %-23s | %6d | %6d |\n", id, surgeryName, empId, medId);
                break;
            }
        }
        System.out.println("+------------+-------------------------+--------+--------+");
    }
    
    //super implementation
    public static void implementUpdate() throws SQLException, ClassNotFoundException {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag != false) {
            System.out.println("Tables to Update\n1.Department\t2.Employee\t3.ICU\t4.OT\t5.Patient\t6.Pharmacy\t7.Surgery\t8.None, Exit");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    doUpdate_department();
                    break;
                case 2:
                    doUpdate_employee();
                    break;
                case 3:
                    doUpdate_icu();
                    break;
                case 4:
                    doUpdate_ot();
                    break;
                case 5:
                    doUpdate_patient();
                    break;
                case 6:
                    doUpdate_pharmacy();
                    break;
                case 7:
                    doUpdate_surgery();
                    break;
                case 8:
                    flag = false;
                    System.out.println("Update operation completed successfully and exited.");
                    break;
                default:
                    System.out.println("Invalid choice entered");
                    break;
            }
        }
    }
}
