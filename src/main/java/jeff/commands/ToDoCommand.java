package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.TaskList;
import jeff.data.task.ToDo;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class ToDoCommand extends Command {
    private ToDo task;

    public ToDoCommand(String description) {
        this.task = new ToDo(description);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTasks(tasks);

            return "Ok, I've added a ToDo task:\n" + task
                    + "\nThere " + ((tasks.getSize() > 1) ? "are " : "is ")
                    + tasks.getSize() + " task(s) in the list.";
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
