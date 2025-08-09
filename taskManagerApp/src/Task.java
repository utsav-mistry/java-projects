import java.time.LocalDate;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private String assignedUser;
    private LocalDate dueDate;
    private String status;

    public Task(int taskId, String title, String description, String assignedUser, LocalDate dueDate, String status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.assignedUser = assignedUser;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and Setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", title=" + title + ", description=" + description + ", assignedUser=" + assignedUser
                + ", dueDate=" + dueDate + ", status=" + status + "]";
    }
}
