package jeff.data.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task with a description and completion status.
 * <p>
 * This class serves as the base class for specific types of tasks such as {@link ToDo},
 * {@link Deadline}, and {@link Event}. It provides functionality for marking tasks as done,
 * generating display strings, and formatting for file storage.
 */
public class Task {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy, HH:mm");
    protected static final DateTimeFormatter FULL_DAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    private String description;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * <p>
     * The task is initially not marked as done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructs a new {@code Task} with the given description and done status.
     *
     * @param description the description of the task
     * @param isDone      {@code true} if the task is completed, {@code false} otherwise
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a one-character icon representing the task's completion status.
     *
     * @return {@code "X"} if the task is done, or a blank space {@code " "} if not done
     */
    public String getIsDoneIcon() {
        return (isDone) ? "X" : " ";

    /**
     * Updates the task's completion status.
     *
     * @param isDone true to mark the task as done, false to mark as not done
     */
    public void updateIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Converts the task to a string suitable for saving to a file.
     * <p>
     * The format is "{doneStatus} | {description}", where doneStatus is
     * {@code 1} if completed, {@code 0} otherwise.
     *
     * @return a string representing the task for storage
     */
    public String toFileString() {
        return String.format("%s | %s", (isDone) ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getIsDoneIcon(), description);
    }
}
