package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class DeleteTaskCommand extends Command {
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) {
        try {
            Task t = tasks.removeTask(index - 1);
            storage.saveTasks(tasks);

            return "Ok, I've removed this task: \n" + t
                    + "\nThere " + ((tasks.size() != 1) ? "are " : "is ")
                    + tasks.size() + " task(s) in the list.";
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
