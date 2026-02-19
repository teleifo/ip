package jeff.commands;

import java.time.LocalDateTime;

import jeff.data.exception.JeffException;
import jeff.data.task.Event;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class EventCommand extends Command {
    private Event task;

    public EventCommand(
            String description, LocalDateTime from,
            LocalDateTime to, boolean isFullDay) {
        this.task = new Event(description, from, to, isFullDay);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTasks(tasks);

            return "Ok, I've added an Event task:\n" + task
                    + "\nThere " + ((tasks.getSize() > 1) ? "are " : "is ")
                    + tasks.getSize() + " task(s) in the list.";
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
