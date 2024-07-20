import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    public void addEvent(Event event) {
        String query = "INSERT INTO events (name, date, time, description) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, event.getName());
            stmt.setDate(2, event.getDate());
            stmt.setTime(3, event.getTime());
            stmt.setString(4, event.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("event_id"));
                event.setName(rs.getString("name"));
                event.setDate(rs.getDate("date"));
                event.setTime(rs.getTime("time"));
                event.setDescription(rs.getString("description"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void updateEvent(Event event) {
        String query = "UPDATE events SET name = ?, date = ?, time = ?, description = ? WHERE event_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, event.getName());
            stmt.setDate(2, event.getDate());
            stmt.setTime(3, event.getTime());
            stmt.setString(4, event.getDescription());
            stmt.setInt(5, event.getEventId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(int eventId) {
        String query = "DELETE FROM events WHERE event_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, eventId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
