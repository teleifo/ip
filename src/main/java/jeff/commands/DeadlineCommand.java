package jeff.commands;

import java.time.LocalDateTime;

import jeff.data.exception.JeffException;
import jeff.data.task.Deadline;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to add a {@link Deadline} task to the task list.
 * <p>
 * Constructs a Deadline task with a description, due date/time, and full-day flag,
 * and executes the addition to the {@link TaskList}, persisting it with {@link Storage}.
 */
public class DeadlineCommand extends Command {
    private Deadline task;

    /**
     * Constructs a {@code DeadlineCommand} with the specified task details.
     *
     * @param description the description of the deadline task
     * @param by the date and time the task is due
     * @param isFullDay whether the task lasts the entire day
     */
    public DeadlineCommand(String description, LocalDateTime by, boolean isFullDay) {
        this.task = new Deadline(description, by, isFullDay);
    }

    /**
     * Executes the command by adding the Deadline task to the provided task list
     * and saving the updated task list to storage.
     * <p>
     * Returns a {@link CommandResult} containing:
     * <ul>
     *     <li>a success message and {@code error=false} if the task was added successfully</li>
     *     <li>an error message and {@code error=true} if an exception occurs</li>
     *     <li>{@code isExit=false} as this command does not terminate the application</li>
     * </ul>
     *
     * @param tasks the task list to add the deadline to
     * @param storage the storage system to persist the updated task list
     * @return a {@link CommandResult} containing message and flags
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTasks(tasks);

            String message = "Ok, I've added a Deadline task:\n" + task
                    + "\nThere " + ((tasks.getSize() > 1) ? "are " : "is ")
                    + tasks.getSize() + " task(s) in the list.";

            return new CommandResult(message, false, false);
        } catch (JeffException e) {
            return new CommandResult(e.getMessage(), true, false);
        }
    }
}
