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
    public String execute(Ui ui, TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTasks(tasks);

            return "Ok, I've added a Deadline task:\n" + task
                    + "\nThere " + ((tasks.size() > 1) ? "are " : "is ")
                    + tasks.size() + " task(s) in the list.";
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
