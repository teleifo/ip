package jeff.commands;

import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to exit the application.
 * <p>
 * When executed, it returns a {@link CommandResult} indicating that the application
 * should terminate. No changes are made to the {@link TaskList} or {@link Storage}.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command by returning a {@link CommandResult} that signals the application to exit.
     *
     * @param tasks the task list
     * @param storage the storage system
     * @return a {@link CommandResult} containing the farewell message and {@code isExit=true}
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(
                "Bye. Hope to see you again soon!\nJeff will close automatically in 5 seconds...",
                false, true
        );
    }
}
