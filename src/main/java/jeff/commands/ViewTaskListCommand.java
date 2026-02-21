package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to view all tasks in the task list.
 * <p>
 * Retrieves tasks from the {@link TaskList} and formats them in a human-readable
 * numbered list. If the task list is empty, an error message is returned.
 */
public class ViewTaskListCommand extends Command {
    /**
     * Executes the command by generating a numbered list of all tasks in the task list.
     *
     * @param tasks the task list to retrieve tasks from
     * @param storage the storage system
     * @return a {@link CommandResult} containing the list of tasks or an error message
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

        try {
            StringBuilder temp = new StringBuilder();
            temp.append("Here are the tasks in your list:\n");

            for (int i = 1; i <= tasks.getSize(); i++) {
                temp.append(i).append(". ").append(tasks.getTask(i - 1));
                if (i != tasks.getSize()) {
                    temp.append("\n");
                }
            }

            return new CommandResult(temp.toString(), false, false);
        } catch (JeffException e) {
            return new CommandResult(e.getMessage(), true, false);
        }
    }
}
