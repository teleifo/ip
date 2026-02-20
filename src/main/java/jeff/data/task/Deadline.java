package jeff.data.task;

import java.time.LocalDateTime;

/**
 * Represents a task that must be completed by a specific date and time.
 * <p>
 * A {@code Deadline} task extends {@link Task} and includes a {@link LocalDateTime}
 * field indicating when the task is due. It also supports full-day tasks that
 * omit the time component when displayed.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private boolean isFullDay;

    /**
     * Constructs a new {@code Deadline} task with the given description and due date.
     * <p>
     * The task is initially not marked as done.
     *
     * @param description the description of the task
     * @param by          the date and time the task is due
     * @param isFullDay   {@code true} if the task should be treated as a full-day task
     */
    public Deadline(String description, LocalDateTime by, boolean isFullDay) {
        this(description, by, isFullDay, false);
    }

    /**
     * Constructs a new {@code Deadline} task with the given description, done status, and due date.
     *
     * @param description the description of the task
     * @param by          the date and time the task is due
     * @param isFullDay   {@code true} if the task should be treated as a full-day task
     * @param isDone      {@code true} if the task is completed, {@code false} otherwise
     */
    public Deadline(String description, LocalDateTime by, boolean isFullDay, boolean isDone) {
        super(description, isDone);

        assert by != null : "Deadline date cannot be null";

        this.by = by;
        this.isFullDay = isFullDay;
    }

    public boolean getIsFullDay() {
        return isFullDay;
    }

    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Converts the task to a string suitable for saving to a file.
     * <p>
     * The format is "D | {super.toFileString()} | {isFullDay} | {by}",
     * where "D" indicates this is a Deadline task and isFullDay is 1 if true, 0 if false.
     *
     * @return a {@link String} representing the Deadline task for storage
     */
    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s",
                super.toFileString(), (isFullDay) ? 1 : 0, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format((isFullDay) ? FULL_DAY_FORMATTER : FORMATTER));
    }
}
