
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jeff {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private static final Path FILE_PATH = Path.of("data", "tasks.txt");

    public Jeff(String filePath) {
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

    public static void main(String[] args)  {
        new Jeff("data/tasks.txt").run();
    }
}
