package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to unmark a task in the task list as not done.
 * <p>
 * Updates the {@link Task} at the specified index in the {@link TaskList}
 * and persists the changes using {@link Storage}.
 */
public class UnmarkTaskCommand extends Command {
    private int index;

    /**
     * Constructs an {@link UnmarkTaskCommand} for the task at the given index.
     *
     * @param index the 1-based index of the task to unmark
     */
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task as not done and saving the task list.
     *
     * @param tasks the task list containing the task
     * @param storage the storage system to persist the updated task list
     * @return a {@link CommandResult} indicating success or containing an error message
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
            Task t = tasks.getTask(index - 1);
            t.updateIsDone(false);

            storage.saveTasks(tasks);

            return new CommandResult(
                    "Ok, I've marked this task as not done:\n" + t,
                    false,
                    false
            );
        } catch (JeffException e) {
            return new CommandResult(e.getMessage(), true, false);
        }
    }
}
