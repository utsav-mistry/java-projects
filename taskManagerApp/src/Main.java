import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static TaskDAO taskDAO = new TaskDAO();
    private static UserDAO userDAO = new UserDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("Task Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Remove Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. View Task by ID");
            System.out.println("6. Add User");
            System.out.println("7. View All Users");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (option) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        updateTask();
                        break;
                    case 3:
                        removeTask();
                        break;
                    case 4:
                        viewAllTasks();
                        break;
                    case 5:
                        viewTaskById();
                        break;
                    case 6:
                        addUser();
                        break;
                    case 7:
                        viewAllUsers();
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void addTask() throws Exception {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Assigned User: ");
        String assignedUser = scanner.nextLine();
        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter Status: ");
        String status = scanner.nextLine();

        Task task = new Task(0, title, description, assignedUser, dueDate, status);
        taskDAO.addTask(task);
        System.out.println("Task added successfully.");
    }

    private static void updateTask() throws Exception {
        System.out.print("Enter Task ID to update: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter New Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter New Assigned User: ");
        String assignedUser = scanner.nextLine();
        System.out.print("Enter New Due Date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter New Status: ");
        String status = scanner.nextLine();

        Task updatedTask = new Task(taskId, title, description, assignedUser, dueDate, status);
        taskDAO.updateTask(updatedTask);
        System.out.println("Task updated successfully.");
    }

    private static void removeTask() throws Exception {
        System.out.print("Enter Task ID to remove: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        taskDAO.removeTask(taskId);
        System.out.println("Task removed successfully.");
    }

    private static void viewAllTasks() throws Exception {
        List<Task> tasks = taskDAO.getAllTasks();
        System.out.println("All Tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static void viewTaskById() throws Exception {
        System.out.print("Enter Task ID: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Task task = taskDAO.getTaskById(taskId);
        if (task != null) {
            System.out.println(task);
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void addUser() throws Exception {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();

        User user = new User(0, username, role);
        userDAO.addUser(user);
        System.out.println("User added successfully.");
    }

    private static void viewAllUsers() throws Exception {
        List<User> users = userDAO.getAllUsers();
        System.out.println("All Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
