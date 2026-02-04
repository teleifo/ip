package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.Deadline;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private Deadline task;

    public DeadlineCommand(String description, LocalDateTime by, boolean isFullDay) {
        this.task = new Deadline(description, by, isFullDay);
    }

    @Override
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        try {
            tasklist.addTask(task);
            storage.saveTasks(tasklist);

            ui.showReply("Ok, I've added a Deadline task:\n" + task
                    + "\nThere " + ((tasklist.size() > 1) ? "are " : "is ")
                    + tasklist.size() + " task(s) in the list.");
        } catch (JeffException e) {
            ui.showError(e.getMessage());
        }
    }
}
