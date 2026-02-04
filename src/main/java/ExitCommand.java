public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        ui.showReply("Bye. Hope to see you again soon!");
    }

    public static boolean isExit(Command c) {
        return c instanceof ExitCommand;
    }
}
