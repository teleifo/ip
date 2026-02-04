package jeff.data.task;

import java.time.format.DateTimeFormatter;

public class Task {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy, HH:mm");
    protected static final DateTimeFormatter FULL_DAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    private String description;
    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getIsDoneIcon() {
        return (isDone) ? "X" : " ";
    }

    public void updateIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toFileString() {
        return String.format("%s | %s", (isDone) ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getIsDoneIcon(), description);
    }
}
