import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static EventDAO eventDAO = new EventDAO();
    private static TicketDAO ticketDAO = new TicketDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Stadium Management System");
            System.out.println("1. Add Event");
            System.out.println("2. View All Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Add Ticket");
            System.out.println("6. View All Tickets");
            System.out.println("7. Update Ticket");
            System.out.println("8. Delete Ticket");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEvent(scanner);
                    break;
                case 2:
                    viewAllEvents();
                    break;
                case 3:
                    updateEvent(scanner);
                    break;
                case 4:
                    deleteEvent(scanner);
                    break;
                case 5:
                    addTicket(scanner);
                    break;
                case 6:
                    viewAllTickets();
                    break;
                case 7:
                    updateTicket(scanner);
                    break;
                case 8:
                    deleteTicket(scanner);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);

        scanner.close();
    }

    private static void addEvent(Scanner scanner) {
        System.out.print("Enter event name: ");
        String name = scanner.next();
        System.out.print("Enter event date (YYYY-MM-DD): ");
        Date date = Date.valueOf(scanner.next());
        System.out.print("Enter event time (HH:MM:SS): ");
        Time time = Time.valueOf(scanner.next());
        System.out.print("Enter event description: ");
        String description = scanner.next();

        Event event = new Event(name, date, time, description);
        eventDAO.addEvent(event);
        System.out.println("Event added successfully.");
    }

    private static void viewAllEvents() {
        List<Event> events = eventDAO.getAllEvents();
        for (Event event : events) {
            System.out.println("Event ID: " + event.getEventId() + ", Name: " + event.getName() +
                    ", Date: " + event.getDate() + ", Time: " + event.getTime() +
                    ", Description: " + event.getDescription());
        }
    }

    private static void updateEvent(Scanner scanner) {
        System.out.print("Enter event ID to update: ");
        int eventId = scanner.nextInt();
        System.out.print("Enter new event name: ");
        String name = scanner.next();
        System.out.print("Enter new event date (YYYY-MM-DD): ");
        Date date = Date.valueOf(scanner.next());
        System.out.print("Enter new event time (HH:MM:SS): ");
        Time time = Time.valueOf(scanner.next());
        System.out.print("Enter new event description: ");
        String description = scanner.next();

        Event event = new Event(name, date, time, description);
        event.setEventId(eventId);
        eventDAO.updateEvent(event);
        System.out.println("Event updated successfully.");
    }

    private static void deleteEvent(Scanner scanner) {
        System.out.print("Enter event ID to delete: ");
        int eventId = scanner.nextInt();
        eventDAO.deleteEvent(eventId);
        System.out.println("Event deleted successfully.");
    }

    private static void addTicket(Scanner scanner) {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter seat number: ");
        String seatNumber = scanner.next();
        System.out.print("Enter ticket price: ");
        double price = scanner.nextDouble();

        Ticket ticket = new Ticket(eventId, customerId, seatNumber, price);
        ticketDAO.addTicket(ticket);
        System.out.println("Ticket added successfully.");
    }

    private static void viewAllTickets() {
        List<Ticket> tickets = ticketDAO.getAllTickets();
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getTicketId() + ", Event ID: " + ticket.getEventId() +
                    ", Customer ID: " + ticket.getCustomerId() + ", Seat Number: " + ticket.getSeatNumber() +
                    ", Price: " + ticket.getPrice());
        }
    }

    private static void updateTicket(Scanner scanner) {
        System.out.print("Enter ticket ID to update: ");
        int ticketId = scanner.nextInt();
        System.out.print("Enter new event ID: ");
        int eventId = scanner.nextInt();
        System.out.print("Enter new customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter new seat number: ");
        String seatNumber = scanner.next();
        System.out.print("Enter new ticket price: ");
        double price = scanner.nextDouble();

        Ticket ticket = new Ticket(eventId, customerId, seatNumber, price);
        ticket.setTicketId(ticketId);
        ticketDAO.updateTicket(ticket);
        System.out.println("Ticket updated successfully.");
    }

    private static void deleteTicket(Scanner scanner) {
        System.out.print("Enter ticket ID to delete: ");
        int ticketId = scanner.nextInt();
        ticketDAO.deleteTicket(ticketId);
        System.out.println("Ticket deleted successfully.");
    }
}
