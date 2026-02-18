package jeff;

import jeff.commands.Command;
import jeff.commands.ExitCommand;
import jeff.data.exception.JeffException;
import jeff.data.task.TaskList;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.ui.Ui;

import java.nio.file.Path;

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

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private boolean isExit;

    /**
     * Constructs a new {@code Jeff} instance.
     * <p>
     * Initializes the UI, storage, and task list. If existing task data
     * cannot be loaded, an empty task list is created and an error
     * message is shown to the user.
     */
    public Jeff() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);

        try {
            storage.ensureDataDirectory();
            tasks = new TaskList(storage.loadTasks());
        } catch (JeffException e) {
            tasks = new TaskList();
        }
    }

    public boolean getIsExit() {
        return isExit;
    }

    public String getGreeting() {
        return GREETING;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            isExit = ExitCommand.isExit(c);

            return c.execute(ui, tasks, storage);
        } catch (JeffException e) {
            return e.getMessage();
        }
    }
}
