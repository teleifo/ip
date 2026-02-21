package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to delete a task from the task list.
 * <p>
 * Executes the deletion of a task at a specified 1-based index in the {@link TaskList},
 * and persists the updated list using {@link Storage}.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Constructs a {@code DeleteTaskCommand} with the specified task index.
     *
     * @param index the 1-based index of the task to delete
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by removing the task at the specified index from the
     * {@link TaskList} and saving the updated task list to storage.
     * <p>
     * Returns a {@link CommandResult} containing:
     * <ul>
     *     <li>a success message and error={@code false} if the task was removed successfully</li>
     *     <li>an error message and error={@code true} if an exception occurs</li>
     *     <li>isExit={@code false} as this command does not terminate the application</li>
     * </ul>
     *
     * @param tasks the task list from which to remove the task
     * @param storage the storage system to persist the updated task list
     * @return a {@link CommandResult} containing message and flags
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            Task t = tasks.removeTask(index - 1);
            storage.saveTasks(tasks);

            String message = "Ok, I've removed this task: \n" + t
                    + "\nThere " + ((tasks.getSize() != 1) ? "are " : "is ")
                    + tasks.getSize() + " task(s) in the list.";

            return new CommandResult(message, false, false);
        } catch (JeffException e) {
            return new CommandResult(e.getMessage(), true, false);
        }
    }
}
