public class ToDo extends Task {
    public ToDo(String description) {
        this(description, false);
    }

    public ToDo(String description, boolean doneStatus) {
        super(description, doneStatus);
    }

    @Override
    public String toFileString() {
        return String.format("T | %s", super.toFileString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
