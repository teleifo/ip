package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class MarkTaskCommand extends Command {
    private int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) {
        try {
            Task t = tasks.getTask(index - 1);
            t.updateIsDone(true);

            storage.saveTasks(tasks);

            return "Ok, I've marked this task as done:\n" + t;
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
