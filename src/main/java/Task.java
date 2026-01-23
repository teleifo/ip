public class Task {
    private String description;
    private boolean doneStatus;

    public Task(String description) {
        this.description = description;
        this.doneStatus = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDoneStatus() {
        return this.doneStatus;
    }

    public String getDoneStatusIcon() {
        return (this.doneStatus) ? "X" : " ";
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateDoneStatus(boolean doneStatus) {
        this.doneStatus = doneStatus;
    }
}
