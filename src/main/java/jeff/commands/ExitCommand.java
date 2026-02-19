package jeff.commands;

import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!\nJeff will close automatically in 5 seconds...";
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
