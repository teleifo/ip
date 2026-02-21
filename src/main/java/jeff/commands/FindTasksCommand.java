package jeff.commands;

import java.util.ArrayList;

import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to search for tasks whose descriptions contain a given query string.
 * <p>
 * When executed, it returns a {@link CommandResult} containing a formatted list of matching tasks.
 * If the task list is empty or no tasks match the query, the result indicates an error.
 */
public class FindTasksCommand extends Command {
    private String query;

    /**
     * Constructs a {@link FindTasksCommand} with the specified search query.
     *
     * @param query the string to search for in task descriptions
     */
    public FindTasksCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the command by searching the {@link TaskList} for tasks containing the query.
     *
     * @param tasks the task list to search
     * @param storage the storage system (unused)
     * @return a {@link CommandResult} containing the matching tasks or an error message
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return new CommandResult(
                    "The task list is currently empty!",
                    true,
                    false
            );
        }

        ArrayList<Task> found = tasks.findTasks(query);

        if (found.isEmpty()) {
            return new CommandResult(
                    "There are no matching tasks in the list!",
                    true,
                    false
            );
        }

        StringBuilder temp = new StringBuilder();
        temp.append("Here are the matching tasks in your list:\n");

        for (int i = 1; i <= found.size(); i++) {
            temp.append(i).append(". ").append(found.get(i - 1));
            if (i != found.size()) {
                temp.append("\n");
            }
        }

        return new CommandResult(temp.toString(), false, false);
    }
}
