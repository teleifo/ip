package jeff.commands;

import java.time.LocalDateTime;

import jeff.data.exception.JeffException;
import jeff.data.task.Deadline;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class DeadlineCommand extends Command {
    private Deadline task;

    public DeadlineCommand(String description, LocalDateTime by, boolean isFullDay) {
        this.task = new Deadline(description, by, isFullDay);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTasks(tasks);

            return "Ok, I've added a Deadline task:\n" + task
                    + "\nThere " + ((tasks.getSize() > 1) ? "are " : "is ")
                    + tasks.getSize() + " task(s) in the list.";
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
