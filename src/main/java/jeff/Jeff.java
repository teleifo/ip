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
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private static final Path FILE_PATH = Path.of("data", "tasks.txt");

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
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command-processing loop of the application.
     * <p>
     * Continuously reads user input, parses commands, and executes them
     * until an exit command is issued.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                ui.showDivider();

                Command c = Parser.parseCommand(userInput);
                c.execute(ui, tasks, storage);

                isExit = ExitCommand.isExit(c);
            } catch (JeffException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * Launches the Jeff application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args)  {
        new Jeff().run();
    }
}
