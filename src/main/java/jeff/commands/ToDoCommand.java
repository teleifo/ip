package jeff.commands;

import jeff.data.exception.JeffException;
import jeff.data.task.TaskList;
import jeff.data.task.ToDo;
import jeff.storage.Storage;

/**
 * Represents a command to add a {@link ToDo} task to the task list.
 * <p>
 * Constructs a ToDo task with a description and executes the addition to the {@link TaskList},
 * persisting it using {@link Storage}.
 */
public class ToDoCommand extends Command {
    private ToDo task;

    /**
     * Constructs a {@link ToDoCommand} for a task with the given description.
     *
     * @param description the description of the ToDo task
     */
    public ToDoCommand(String description) {
        this.task = new ToDo(description);
    }

    /**
     * Executes the command by adding the ToDo task to the task list and saving it.
     *
     * @param tasks the task list to add the task to
     * @param storage the storage system to persist the updated task list
     * @return a {@link CommandResult} indicating success or containing an error message
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTasks(tasks);

            String message = "Ok, I've added a ToDo task:\n" + task
                    + "\nThere " + ((tasks.getSize() > 1) ? "are " : "is ")
                    + tasks.getSize() + " task(s) in the list.";
            return new CommandResult(message, false, false);
        } catch (JeffException e) {
            return new CommandResult(e.getMessage(), true, false);
        }
    }
}
