package jeff;

import java.nio.file.Path;

import jeff.commands.Command;
import jeff.commands.CommandResult;
import jeff.data.exception.JeffException;
import jeff.data.task.TaskList;
import jeff.parser.Parser;
import jeff.storage.Storage;

/**
 * The main entry point of the Jeff application.
 * <p>
 * {@code Jeff} is a command-line task management program that reads and
 * executes user commands, and persists tasks to local storage.
 */
public class Jeff {
    private static final String GREETING = """
            Hello! I'm Jeff.
            What can I do for you?""";

    private static final Path FILE_PATH = Path.of("data", "tasks.txt");

    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a new {@code Jeff} instance.
     * <p>
     * Initializes the storage, and task list. If existing task data
     * cannot be loaded, an empty task list is created and an error
     * message is shown to the user.
     */
    public Jeff() {
        storage = new Storage(FILE_PATH);

        try {
            storage.ensureDataDirectory();
            tasks = new TaskList(storage.loadTasks());
        } catch (JeffException e) {
            tasks = new TaskList();
        }
    }

    public String getGreeting() {
        return GREETING;
    }

    public CommandResult getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);

            return c.execute(tasks, storage);
        } catch (JeffException e) {
            return new CommandResult(e.getMessage(), true, false);
        }
    }
}
