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
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        try {
            tasklist.addTask(task);
            storage.saveTasks(tasklist);

            ui.showReply("Ok, I've added a ToDo task:\n" + task
                    + "\nThere " + ((tasklist.size() > 1) ? "are " : "is ")
                    + tasklist.size() + " task(s) in the list.");
        } catch (JeffException e) {
            ui.showError(e.getMessage());
        }
    }
}
