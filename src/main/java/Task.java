import java.time.format.DateTimeFormatter;

public class Task {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy, HH:mm");
    protected static final DateTimeFormatter FULL_DAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    private String description;
    private boolean doneStatus;

    public Task(String description) {
        this.description = description;
        this.doneStatus = false;
    }

    public Task(String description, boolean doneStatus) {
        this.description = description;
        this.doneStatus = doneStatus;
    }

    public String getDoneStatusIcon() {
        return (doneStatus) ? "X" : " ";
    }

    public void updateDoneStatus(boolean doneStatus) {
        this.doneStatus = doneStatus;
    }

    public String toFileString() {
        return String.format("%s | %s", (doneStatus) ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatusIcon(), description);
    }
}
