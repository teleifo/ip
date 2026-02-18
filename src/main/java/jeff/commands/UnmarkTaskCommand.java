package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class UnmarkTaskCommand extends Command {
    private int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "The task list is currently empty!";
        }

        try {
            Task t = tasks.getTask(index - 1);
            t.updateIsDone(false);

            storage.saveTasks(tasks);

            return "Ok, I've marked this task as not done:\n" + t;
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
