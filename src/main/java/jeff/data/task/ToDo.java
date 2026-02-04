package jeff.data.task;

public class ToDo extends Task {
    public ToDo(String description) {
        this(description, false);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
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
