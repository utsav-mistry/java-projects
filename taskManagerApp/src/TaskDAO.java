import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, assigned_user, due_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getAssignedUser());
            stmt.setDate(4, Date.valueOf(task.getDueDate()));
            stmt.setString(5, task.getStatus());
            stmt.executeUpdate();
        }
    }

    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, assigned_user = ?, due_date = ?, status = ? WHERE task_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getAssignedUser());
            stmt.setDate(4, Date.valueOf(task.getDueDate()));
            stmt.setString(5, task.getStatus());
            stmt.setInt(6, task.getTaskId());
            stmt.executeUpdate();
        }
    }

    public void removeTask(int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE task_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        }
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int taskId = rs.getInt("task_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String assignedUser = rs.getString("assigned_user");
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                String status = rs.getString("status");
                tasks.add(new Task(taskId, title, description, assignedUser, dueDate, status));
            }
        }
        return tasks;
    }

    public Task getTaskById(int taskId) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE task_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String assignedUser = rs.getString("assigned_user");
                    LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                    String status = rs.getString("status");
                    return new Task(taskId, title, description, assignedUser, dueDate, status);
                }
            }
        }
        return null;
    }
}
