import java.util.Scanner;

public class app {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean isAuthenticated = false;

        // Loop for continuous operation
        while (true) {
            // Authenticate if not already authenticated
            if (!isAuthenticated) {
                isAuthenticated = authenticator.authenticate();
                if (!isAuthenticated) {
                    System.out.println("Authentication failed. Please try again.");
                    continue;
                }
            }

            // Display menu
            System.out.println("\nHospital Management System Menu:");
            System.out.println("1. Set Appointment");
            System.out.println("2. Update Data");
            System.out.println("3. Select Data");
            System.out.println("4. Delete Data");
            System.out.println("5. Insert Data");
            System.out.println("6. Logout and Exit");
            System.out.print("Enter your choice: ");

            // Read user choice
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character after nextInt()

            // Perform actions based on user choice
            switch (choice) {
                case 1:
                    appointment.setAppointment();
                    break;
                case 2:
                    update.implementUpdate();
                    break;
                case 3:
                    select.implementSelect();
                    break;
                case 4:
                    delete.implementDelete();
                    break;
                case 5:
                    insert.implementInsert();
                    break;
                case 6:
                    System.out.println("Logging out and exiting program...");
                    return; // Exit the main method and end the program
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}
