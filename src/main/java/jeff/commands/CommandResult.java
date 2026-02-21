package jeff.commands;

/**
 * Represents the result of executing a {@link Command}.
 * <p>
 * Contains a message to display to the user, a flag indicating whether the
 * result represents an error, and a flag indicating whether the command
 * should terminate the application.
 */
public class CommandResult {
    private final String message;
    private final boolean isError;
    private final boolean isExit;

    /**
     * Constructs a {@code CommandResult}.
     *
     * @param message the message to display to the user
     * @param isError true if this result represents an error
     * @param isExit true if the command should exit the application
     */
    public CommandResult(String message, boolean isError, boolean isExit) {
        this.message = message;
        this.isError = isError;
        this.isExit = isExit;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsError() {
        return isError;
    }

    public boolean getIsExit() {
        return isExit;
    }
}
