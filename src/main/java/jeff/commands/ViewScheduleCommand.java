package jeff.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to view all tasks scheduled for a specific date.
 * <p>
 * Filters {@link TaskList} for tasks occurring on the given date and formats
 * them in a human-readable schedule.
 */
public class ViewScheduleCommand extends Command {
    private String query;

    /**
     * Constructs a {@link ViewScheduleCommand} for the given date string.
     *
     * @param query the date string in ISO format (yyyy-MM-dd)
     */
    public ViewScheduleCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the command by retrieving all tasks on the specified date
     * and returning a formatted schedule message.
     *
     * @param tasks the task list to search
     * @param storage the storage system
     * @return a {@link CommandResult} with the schedule or an error message
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

        LocalDate date = LocalDate.parse(query);

        ArrayList<Task> found = tasks.viewSchedule(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        if (found.isEmpty()) {
            return new CommandResult(
                    "No tasks found on " + date.format(formatter) + "!",
                    true,
                    false
            );
        }

        StringBuilder temp = new StringBuilder();
        temp.append("Here's your schedule for " + date.format(formatter) + ":\n");

        for (int i = 1; i <= found.size(); i++) {
            temp.append(i).append(". ").append(found.get(i - 1));
            if (i != found.size()) {
                temp.append("\n");
            }
        }

        return new CommandResult(temp.toString(), false, false);
    }
}
