package jeff.commands;

import jeff.data.task.TaskList;
import jeff.storage.Storage;

/**
 * Represents a command for handling unrecognised or invalid input.
 * <p>
 * When executed, it returns a {@link CommandResult} indicating
 * that the command is unknown.
 */
public class UnknownCommand extends Command {
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String message = """
                Sorry, I don't know what that means!
                Type `help` to view available commands.
                """;

        return new CommandResult(message, true, false);
    }
}
