public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean doneStatus, String by) {
        super(description, doneStatus);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s", super.toFileString(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
