package jeff.commands;

import java.time.LocalDateTime;

import jeff.data.exception.JeffException;
import jeff.data.task.Event;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to add an {@link Event} task to the task list.
 * <p>
 * Constructs an Event task with a description, start and end date/time, and a full-day flag,
 * and executes the addition to the {@link TaskList}, persisting it with {@link Storage}.
 */
public class EventCommand extends Command {
    private Event task;

    /**
     * Constructs an {@code EventCommand} with the specified parameters.
     *
     * @param description the description of the event
     * @param from the start date/time of the event
     * @param to the end date/time of the event
     * @param isFullDay whether the event spans the full day
     */
    public EventCommand(
            String description,
            LocalDateTime from,
            LocalDateTime to,
            boolean isFullDay
    ) {
        this.task = new Event(description, from, to, isFullDay);
    }

    /**
     * Executes the command by adding the Event task to the {@link TaskList} and
     * saving the updated list to {@link Storage}.
     * <p>
     * Returns a {@link CommandResult} containing:
     * <ul>
     *     <li>a success message and {@code isError=false} if the task was added successfully</li>
     *     <li>an error message and {@code isError=true} if an exception occurs</li>
     *     <li>{@code isExit=false} as this command does not terminate the application</li>
     * </ul>
     *
     * @param tasks the task list to add the event to
     * @param storage the storage system to persist the updated task list
     * @return a {@link CommandResult} containing the execution result
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(task);
            storage.saveTasks(tasks);

            String message = "Ok, I've added an Event task:\n" + task
                    + "\nThere " + ((tasks.getSize() > 1) ? "are " : "is ")
                    + tasks.getSize() + " task(s) in the list.";

            return new CommandResult(message, false, false);
        } catch (JeffException e) {
            return new CommandResult(e.getMessage(), true, false);
        }
    }
}
