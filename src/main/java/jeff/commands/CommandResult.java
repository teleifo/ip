package jeff.commands;

/**
 * Represents the result of executing a {@link Command}.
 * <p>
 * Contains a message to display to the user, and flags indicating whether the
 * result represents an error, whether the command should terminate the application,
 * and whether the result is a help message.
 */
public class CommandResult {
    private final String message;
    private final boolean isError;
    private final boolean isExit;
    private final boolean isHelp;

    /**
     * Constructs a {@code CommandResult} with no help flag.
     *
     * @param message the message to display to the user
     * @param isError true if this result represents an error
     * @param isExit true if the command should exit the application
     */
    public CommandResult(String message, boolean isError, boolean isExit) {
        this(message, isError, isExit, false);
    }

    /**
     * Constructs a {@code CommandResult} with all flags specified.
     *
     * @param message the message to display to the user
     * @param isError true if this result represents an error
     * @param isExit true if the command should exit the application
     * @param isHelp true if the result represents a help message
     */
    public CommandResult(String message, boolean isError, boolean isExit, boolean isHelp) {
        this.message = message;
        this.isError = isError;
        this.isExit = isExit;
        this.isHelp = isHelp;
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

    public boolean getIsHelp() {
        return isHelp;
    }
}
