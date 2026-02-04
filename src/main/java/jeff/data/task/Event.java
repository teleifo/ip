package jeff.data.task;

import java.time.LocalDateTime;

/**
 * Represents an event task that occurs within a specific time range.
 * <p>
 * An {@code Event} extends {@link Task} and includes start and end {@link LocalDateTime}
 * fields to represent the duration of the event. Supports full-day events where
 * only the date is shown.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean isFullDay;

    /**
     * Constructs a new {@code Event} task with the given description and time range.
     * <p>
     * The task is initially not marked as done.
     *
     * @param description the description of the event
     * @param from        the start date and time of the event
     * @param to          the end date and time of the event
     * @param isFullDay   {@code true} if the event should be treated as full-day
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isFullDay) {
        this(description, false, from, to, isFullDay);
    }

    /**
     * Constructs a new {@code Event} task with the given description, done status, and time range.
     *
     * @param description the description of the event
     * @param isDone      {@code true} if the event is completed, {@code false} otherwise
     * @param from        the start date and time of the event
     * @param to          the end date and time of the event
     * @param isFullDay   {@code true} if the event should be treated as full-day
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to, boolean isFullDay) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.isFullDay = isFullDay;
    }

    /**
     * Converts the event to a string suitable for saving to a file.
     * <p>
     * The format is "E | {super.toFileString()} | {isFullDay} | from {from} to {to}",
     * where "E" indicates an Event task and isFullDay is 1 if true, 0 if false.
     *
     * @return a string representing the Event task for storage
     */
    @Override
    public String toFileString() {
        return String.format("E | %s | %s | from %s to %s",
                super.toFileString(), (isFullDay) ? 1 : 0, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(),
                from.format((isFullDay) ? FULL_DAY_FORMATTER : FORMATTER),
                to.format((isFullDay) ? FULL_DAY_FORMATTER : FORMATTER));
    }
}
