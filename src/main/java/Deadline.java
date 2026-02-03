import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;
    private boolean isFullDay;

    public Deadline(String description, LocalDateTime by, boolean isFullDay) {
        super(description);
        this.by = by;
        this.isFullDay = isFullDay;
    }

    public Deadline(String description, boolean doneStatus, LocalDateTime by, boolean isFullDay) {
        super(description, doneStatus);
        this.by = by;
        this.isFullDay = isFullDay;
    }

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
