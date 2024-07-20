import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class Appointment {
    String f_name;
    String l_name;
    String address;
    int age;
    String bloodgroup;

    Appointment(String f_name, String l_name, String address, int age, String bloodgroup) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.address = address;
        this.age = age;
        this.bloodgroup = bloodgroup;
    }

    @Override
    public String toString() {
        return f_name + "|" + l_name + "|" + address + "|" + age + "|" + bloodgroup;
    }
}

public class appointment {
    private static final String FILE_PATH = "C:/Users/UTSAV/Downloads/Project/hospital/lib/appointments.txt";
    private static ArrayList<Appointment> appointments;
    private static Connection connection;

    public appointment() throws ClassNotFoundException, SQLException {
        appointments = new ArrayList<>();
        connect.doConnectReception();
        loadAppointmentsFromFile();
    }

    private void loadAppointmentsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 5) {
                    try {
                        Appointment ap = new Appointment(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
                        appointments.add(ap);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing age: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void saveAppointmentsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Appointment appointment : appointments) {
                bw.write(appointment.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        saveAppointmentsToFile();
        System.out.println("Appointment added successfully: " + appointment);
    }

    public static void deleteFirstAppointment() {
        if (!appointments.isEmpty()) {
            Appointment deletedAppointment = appointments.remove(0);
            saveAppointmentsToFile();

            @SuppressWarnings ("resource")
            Scanner sc = new Scanner(System.in);
            System.out.println("Deleted appointment: " + deletedAppointment);
            System.out.print("Add to patient table? [Y/N]: ");
            String choice = sc.next().toUpperCase();

            if (choice.equals("Y")) {
                addToPatientTable(deletedAppointment);
            } else if (!choice.equals("N")) {
                System.out.println("Invalid choice. Not adding to patient table.");
            }
        } else {
            System.out.println("No appointments to delete.");
        }
    }

    private static void addToPatientTable(Appointment appointment) {
        if (connection == null) {
            System.err.println("Connection is not initialized.");
            return;
        }

        try {
            String sql = "INSERT INTO patient (f_name, l_name, address, age, bloodgroup) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, appointment.f_name);
            pst.setString(2, appointment.l_name);
            pst.setString(3, appointment.address);
            pst.setInt(4, appointment.age);
            pst.setString(5, appointment.bloodgroup);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted into patient table: " + appointment);
            }
        } catch (SQLException e) {
            System.err.println("Error inserting into patient table: " + e.getMessage());
        }
    }

    public static void setAppointment() throws ClassNotFoundException, SQLException {
        new appointment();
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Existing Appointments:");
            for (Appointment appointment : appointments) {
                System.out.println(appointment.toString());
            }
            System.out.println("------------------------");

            System.out.println("Enter choice:\n1. Insert\t2. Delete\t3. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter new appointment details: f_name, l_name, address, age, bloodgroup");
                    System.out.print("First Name : ");
                    String f_name = sc.next();
                    System.out.print("Last Name : ");
                    String l_name = sc.next();
                    System.out.print("Address : ");
                    sc.nextLine(); // Consume newline left-over
                    String address = sc.nextLine();
                    System.out.print("Age : ");
                    int age = sc.nextInt();
                    System.out.print("Bloodgroup : ");
                    String bloodgroup = sc.next();
                    Appointment newAppointment = new Appointment(f_name, l_name, address, age, bloodgroup);
                    addAppointment(newAppointment);

                        System.out.println("Updated Appointments:");
                    for (Appointment appointment : appointments) {
                        System.out.println(appointment.toString());
                    }
                    System.out.println("------------------------");
                    break;
                case 2:
                    deleteFirstAppointment();
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}
