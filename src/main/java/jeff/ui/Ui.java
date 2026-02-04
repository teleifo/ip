package jeff.ui;

import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

/**
 * Handles all input and output interactions with the user.
 * <p>
 * The {@code Ui} class provides methods to read user input,
 * display messages, and show errors.
 */
public class Ui {
    private static final String GREETING = """
            Hello! I'm Jeff.
            What can I do for you?""";

    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a {@code Ui} that reads from {@link System#in} and writes to {@link System#out}.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a {@code Ui} with a custom input stream and output stream.
     *
     * @param in  the input stream to read user input from
     * @param out the print stream to write output to
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints a horizontal divider line.
     */
    public void showDivider() {
        out.println(DIVIDER);
    }

    /**
     * Prints the greeting message to the user, surrounded by dividers.
     */
    public void showGreeting() {
        showDivider();
        out.println(GREETING);
        showDivider();
    }

    /**
     * Prints a reply message to the user.
     *
     * @param message the message to display
     */
    public void showReply(String message) {
        out.println(message);
    }

    /**
     * Prints an error message to the user with a standard error prefix.
     *
     * @param error the error message to display
     */
    public void showError(String error) {
        out.printf("<! Error !> %s%n", error);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the user input as a string
     */
    public String readCommand() {
        return in.nextLine();
    }
}
