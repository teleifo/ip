public class DeleteTaskCommand extends Command {
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        try {
            Task t = tasklist.removeTask(index - 1);
            storage.saveTasks(tasklist);

            ui.showReply("Ok, I've removed this task: \n" + t
                    + "\nThere " + ((tasklist.size() != 1) ? "are " : "is ")
                    + tasklist.size() + " task(s) in the list.");
        } catch (JeffException e) {
            ui.showError(e.getMessage());
        }
    }
}
