import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class delete {
    static Statement s;
    static PreparedStatement pst;

    // table department
    @SuppressWarnings("resource")
    public static void doDelete_department() throws SQLException, ClassNotFoundException {
        if (!connect.type.equals("admin")) {
            System.out.println("Can't delete, only admin has privileges.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String sql = "DELETE FROM `department` WHERE dep_id = ?";

        s = connect.connectAdmin.createStatement();
        pst = connect.connectAdmin.prepareStatement(sql);

        System.out.println("Department ID: ");
        int depId = sc.nextInt();

        pst.setInt(1, depId);

        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Delete failed, no department found with ID " + depId);
        } else {
            System.out.println("Department with ID " + depId + " deleted successfully.");
        }
    }

    // table employee
    @SuppressWarnings("resource")
    public static void doDelete_employee() throws SQLException, ClassNotFoundException {
        if (!connect.type.equals("admin")) {
            System.out.println("Can't delete, only admin has privileges.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String sql = "DELETE FROM `employee` WHERE emp_id = ?";

        s = connect.connectAdmin.createStatement();
        pst = connect.connectAdmin.prepareStatement(sql);

        System.out.println("Employee ID: ");
        int empId = sc.nextInt();

        pst.setInt(1, empId);

        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Delete failed, no employee found with ID " + empId);
        } else {
            System.out.println("Employee with ID " + empId + " deleted successfully.");
        }
    }

    // table icu
    @SuppressWarnings("resource")
    public static void doDelete_icu() throws SQLException, ClassNotFoundException {
        if (!connect.type.equals("admin")) {
            System.out.println("Can't delete, only admin has privileges.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String sql = "DELETE FROM `icu` WHERE icu_id = ?";

        s = connect.connectAdmin.createStatement();
        pst = connect.connectAdmin.prepareStatement(sql);

        System.out.println("ICU ID: ");
        int icuId = sc.nextInt();

        pst.setInt(1, icuId);

        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Delete failed, no ICU found with ID " + icuId);
        } else {
            System.out.println("ICU with ID " + icuId + " deleted successfully.");
        }
    }

    // table ot
    @SuppressWarnings("resource")
    public static void doDelete_ot() throws SQLException, ClassNotFoundException {
        if (!connect.type.equals("admin")) {
            System.out.println("Can't delete, only admin has privileges.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String sql = "DELETE FROM `ot` WHERE ot_id = ?";

        s = connect.connectAdmin.createStatement();
        pst = connect.connectAdmin.prepareStatement(sql);

        System.out.println("OT ID: ");
        int otId = sc.nextInt();

        pst.setInt(1, otId);

        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Delete failed, no OT found with ID " + otId);
        } else {
            System.out.println("OT with ID " + otId + " deleted successfully.");
        }
    }

    // table patient
    @SuppressWarnings("resource")
    public static void doDelete_patient() throws SQLException, ClassNotFoundException {
        if (!connect.type.equals("admin")) {
            System.out.println("Can't delete, only admin has privileges.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String sql = "DELETE FROM `patient` WHERE patient_id = ?";

        s = connect.connectAdmin.createStatement();
        pst = connect.connectAdmin.prepareStatement(sql);

        System.out.println("Patient ID: ");
        int patientId = sc.nextInt();

        pst.setInt(1, patientId);

        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Delete failed, no patient found with ID " + patientId);
        } else {
            System.out.println("Patient with ID " + patientId + " deleted successfully.");
        }
    }

    // table pharmacy
    @SuppressWarnings("resource")
    public static void doDelete_pharmacy() throws SQLException, ClassNotFoundException {
        if (!connect.type.equals("admin")) {
            System.out.println("Can't delete, only admin has privileges.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String sql = "DELETE FROM `pharmacy` WHERE pharmacy_id = ?";

        s = connect.connectAdmin.createStatement();
        pst = connect.connectAdmin.prepareStatement(sql);

        System.out.println("Pharmacy ID: ");
        int pharmacyId = sc.nextInt();

        pst.setInt(1, pharmacyId);

        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Delete failed, no pharmacy found with ID " + pharmacyId);
        } else {
            System.out.println("Pharmacy with ID " + pharmacyId + " deleted successfully.");
        }
    }

    // table surgery
    @SuppressWarnings("resource")
    public static void doDelete_surgery() throws SQLException, ClassNotFoundException {
        if (!connect.type.equals("admin")) {
            System.out.println("Can't delete, only admin has privileges.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String sql = "DELETE FROM `surgery` WHERE surgery_id = ?";

        s = connect.connectAdmin.createStatement();
        pst = connect.connectAdmin.prepareStatement(sql);

        System.out.println("Surgery ID: ");
        int surgeryId = sc.nextInt();

        pst.setInt(1, surgeryId);

        int r = pst.executeUpdate();
        if (r == 0) {
            System.out.println("Delete failed, no surgery found with ID " + surgeryId);
        } else {
            System.out.println("Surgery with ID " + surgeryId + " deleted successfully.");
        }
    }

    // super implementation
    public static void implementDelete() throws SQLException, ClassNotFoundException {
        if (!connect.type.equals("admin")) {
            System.out.println("Can't delete, only admin has privileges.");
            return;
        }

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Tables to delete from\n1.Department\t2.Employee\t3.ICU\t4.OT\t5.Patient\t6.Pharmacy\t7.Surgery\t8.None, Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    doDelete_department();
                    break;
                case 2:
                    doDelete_employee();
                    break;
                case 3:
                    doDelete_icu();
                    break;
                case 4:
                    doDelete_ot();
                    break;
                case 5:
                    doDelete_patient();
                    break;
                case 6:
                    doDelete_pharmacy();
                    break;
                case 7:
                    doDelete_surgery();
                    break;
                case 8:
                    flag = false;
                    System.out.println("Delete operation completed successfully and exited.");
                    break;
                default:
                    System.out.println("Invalid choice entered");
                    break;
            }
        }
    }
}
