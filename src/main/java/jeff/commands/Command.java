package jeff.commands;

import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a generic command in Jeff.
 * <p>
 * Subclasses should override {@link #execute(TaskList, Storage)} to define specific behaviour.
 */
public class Command {
    /**
     * Executes the command using the provided {@link TaskList} and {@link Storage}.
     *
     * @param tasks   the task list to operate on
     * @param storage the storage for persisting changes
     * @return a {@link CommandResult} containing the message, error status, and exit flag
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        return null;
    }
}
