package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class ViewTaskListCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        if (tasklist.isEmpty()) {
            ui.showReply("The task list is currently empty!");
            return;
        }

        try {
            StringBuilder temp = new StringBuilder();
            temp.append("Here are the tasks in your list:\n");

            for (int i = 1; i <= tasklist.size(); i++) {
                temp.append(i).append(". ").append(tasklist.getTask(i - 1));
                if (i != tasklist.size()) temp.append("\n");
            }

            ui.showReply(temp.toString());
        } catch (JeffException e) {
            ui.showError(e.getMessage());
        }
    }
}
