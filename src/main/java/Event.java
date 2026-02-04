import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean isFullDay;

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isFullDay) {
        this(description, false, from, to, isFullDay);
    }

    public Event(String description, boolean doneStatus, LocalDateTime from, LocalDateTime to, boolean isFullDay) {
        super(description, doneStatus);
        this.from = from;
        this.to = to;
        this.isFullDay = isFullDay;
    }

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
