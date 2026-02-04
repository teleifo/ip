import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

public class Ui {
    private static final String GREETING = """
            Hello! I'm Jeff.
            What can I do for you?""";

    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showDivider() {
        out.println(DIVIDER);
    }

    public void showGreeting() {
        showDivider();
        out.println(GREETING);
        showDivider();
    }

    public void showReply(String message) {
        out.println(message);
    }

    public void showError(String error) {
        out.printf("<! Error !> %s%n", error);
    }

    public String readCommand() {
        return in.nextLine();
    }
}
