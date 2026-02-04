package jeff.data.exception;

/**
 * Represents exceptions specific to the Jeff application.
 * <p>
 * This exception is used to signal errors in storage, parsing, commands, or other
 * application-specific operations.
 */
public class JeffException extends Exception {

    /**
     * Constructs a new {@code JeffException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public JeffException(String message) {
        super(message);
    }
}
