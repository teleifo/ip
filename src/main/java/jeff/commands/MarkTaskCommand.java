package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to mark a task in the task list as done.
 * <p>
 * When executed, it updates the specified task’s status to done and persists
 * the change using {@link Storage}.
 */
public class MarkTaskCommand extends Command {
    private int index;

    /**
     * Constructs a {@link MarkTaskCommand} for the task at the given index.
     *
     * @param index the 1-based index of the task to mark as done
     */
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the specified task as done.
     *
     * @param tasks the task list containing the task
     * @param storage the storage system to persist the change
     * @return a {@link CommandResult} indicating success or containing an error message
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            Task t = tasks.getTask(index - 1);
            t.updateIsDone(true);

            storage.saveTasks(tasks);

            String message = "Ok, I've marked this task as done:\n" + t;
            return new CommandResult(message, false, false);
        } catch (JeffException e) {
            return new CommandResult(e.getMessage(), true, false);
        }
    }
}
