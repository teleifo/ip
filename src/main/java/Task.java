public class Task {
    private String description;
    private boolean doneStatus;

    public Task(String description) {
        this.description = description;
        this.doneStatus = false;
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

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatusIcon(), description);
    }
}
