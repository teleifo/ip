public class Task {
    private String description;
    private boolean doneStatus;

    public Task(String description) {
        this.description = description;
        this.doneStatus = false;
    }

    public Task(String description, boolean doneStatus) {
        this.description = description;
        this.doneStatus = doneStatus;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDoneStatus() {
        return doneStatus;
    }

    public String getDoneStatusIcon() {
        return (doneStatus) ? "X" : " ";
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateDoneStatus(boolean doneStatus) {
        this.doneStatus = doneStatus;
    }

    public String toFileString() {
        return String.format("%s | %s", (doneStatus) ? 1 : 0, description);
    }

    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ");

        switch (parts[0]) {
            case "T":
                return new ToDo(parts[2], parts[1].equals("1"));
            case "D":
                return new Deadline(parts[2], parts[1].equals("1"), parts[3]);
            case "E":
                String timeRange = parts[3].substring(5);
                String[] fromTo = timeRange.split(" to ");
                return new Event(parts[2], parts[1].equals("1"), fromTo[0], fromTo[1]);
            default:
                throw new IllegalArgumentException("Invalid task format");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatusIcon(), description);
    }
}
