public class Ticket {
    private int ticketId;
    private int eventId;
    private int customerId;
    private String seatNumber;
    private double price;

    // Constructors, getters, and setters
    public Ticket() {}

    public Ticket(int eventId, int customerId, String seatNumber, double price) {
        this.eventId = eventId;
        this.customerId = customerId;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
