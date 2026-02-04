package jeff.commands;

import java.util.ArrayList;

import jeff.data.exception.JeffException;
import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class FindTasksCommand extends Command {
    private String query;

    public FindTasksCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showReply("The task list is currently empty!");
            return;
        }

        ArrayList<Task> found = tasks.findTasks(query);

        if (found.isEmpty()) {
            ui.showReply("There are no matching tasks in the list!");
            return;
        }

        StringBuilder temp = new StringBuilder();
        temp.append("Here are the matching tasks in your list:\n");

        for (int i = 1; i <= found.size(); i++) {
            temp.append(i).append(". ").append(found.get(i - 1));
            if (i != found.size()) temp.append("\n");
        }

        ui.showReply(temp.toString());
    }
}
