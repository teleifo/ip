import java.time.LocalDateTime;

public class EventCommand extends Command {
    private Event task;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to, boolean isFullDay) {
        this.task = new Event(description, from, to, isFullDay);
    }

    @Override
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        try {
            tasklist.addTask(task);
            storage.saveTasks(tasklist);

            ui.showReply("Ok, I've added an Event task:\n" + task
                    + "\nThere " + ((tasklist.size() > 1) ? "are " : "is ")
                    + tasklist.size() + " task(s) in the list.");
        } catch (JeffException e) {
            ui.showError(e.getMessage());
        }
    }
}
