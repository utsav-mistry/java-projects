import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    public void addTicket(Ticket ticket) {
        String query = "INSERT INTO tickets (event_id, customer_id, seat_number, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticket.getEventId());
            stmt.setInt(2, ticket.getCustomerId());
            stmt.setString(3, ticket.getSeatNumber());
            stmt.setDouble(4, ticket.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setEventId(rs.getInt("event_id"));
                ticket.setCustomerId(rs.getInt("customer_id"));
                ticket.setSeatNumber(rs.getString("seat_number"));
                ticket.setPrice(rs.getDouble("price"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public void updateTicket(Ticket ticket) {
        String query = "UPDATE tickets SET event_id = ?, customer_id = ?, seat_number = ?, price = ? WHERE ticket_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticket.getEventId());
            stmt.setInt(2, ticket.getCustomerId());
            stmt.setString(3, ticket.getSeatNumber());
            stmt.setDouble(4, ticket.getPrice());
            stmt.setInt(5, ticket.getTicketId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTicket(int ticketId) {
        String query = "DELETE FROM tickets WHERE ticket_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticketId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
