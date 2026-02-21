package jeff.commands;

import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command to display the help message in Jeff.
 * <p>
 * When executed, it returns a {@link CommandResult} containing
 * the formatted list of available commands and their descriptions.
 */
public class HelpCommand extends Command {
    /**
     * Executes the command by searching the {@link TaskList} for tasks containing the query.
     *
     * @param tasks the task list to search
     * @param storage the storage system
     * @return a {@link CommandResult} containing the list of available commands
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String message = """
                Here's what Jeff can do for you!
                todo [desc] - Add a simple task
                deadline [desc] /by [time] - Add a task with a deadline
                event [desc] /from [start] /to [end] - Add an event with start and end times
                list - Show all tasks in your list
                find [keyword] - Search tasks containing a keyword
                schedule [date] - Show tasks scheduled for a specific date
                mark [task id] - Mark a task as completed
                unmark [task id] - Mark a task as not completed
                delete [task id] - Remove a task from your list
                help - Display this help message!
                bye - Exit Jeff :(
                """;

        return new CommandResult(message, false, false, true);
    }
}
