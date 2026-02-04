package jeff.data.task;

/**
 * Represents a simple "To Do" task without any specific date or time.
 * <p>
 * A {@code ToDo} task extends {@link Task} and overrides methods to include
 * a task type identifier ("T") for display and file storage purposes.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the given description.
     * <p>
     * The task is initially not marked as done.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Constructs a new {@code ToDo} task with the given description and done status.
     *
     * @param description the description of the task
     * @param doneStatus  {@code true} if the task is completed, {@code false} otherwise
     */
    public ToDo(String description, boolean doneStatus) {
        super(description, doneStatus);
    }

    /**
     * Converts the task to a string suitable for saving to a file.
     * <p>
     * The format is "T | {doneStatus} | {description}", where "T" indicates
     * that this is a ToDo task, and doneStatus is 1 if completed, 0 otherwise.
     *
     * @return a string representing the ToDo task for storage
     */
    @Override
    public String toFileString() {
        return String.format("T | %s", super.toFileString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
