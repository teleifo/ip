package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class ViewTaskListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "The task list is currently empty!";
        }

        try {
            StringBuilder temp = new StringBuilder();
            temp.append("Here are the tasks in your list:\n");

            for (int i = 1; i <= tasks.getSize(); i++) {
                temp.append(i).append(". ").append(tasks.getTask(i - 1));
                if (i != tasks.getSize()) {
                    temp.append("\n");
                }
            }

            return temp.toString();
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
