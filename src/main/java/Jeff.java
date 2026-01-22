import java.util.Scanner;
import java.util.ArrayList;

public class Jeff {
    private static final ArrayList<String> list = new ArrayList<String>();

    private static String formatReply(String message) {
        return String.format("""
                ____________________________________________________________
                %s
                ____________________________________________________________""", message);
    }

    private static String handleCommand(String command) {
        if (command.equals("list")) {
            if (list.isEmpty()) {
                return formatReply("List is currently empty!");
            } else {
                StringBuilder temp = new StringBuilder();
                for (int i = 1; i <= list.size(); i++) {
                    temp.append(i).append(". ").append(list.get(i-1));
                    if (i != list.size()) temp.append("\n");
                }
                return formatReply(temp.toString());
            }
        } else if (command.equals("bye")) {
            return formatReply("Bye. Hope to see you again soon!");
        } else {
            list.add(command);
            return formatReply(String.format("Added: %s", command));
        }
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        String greeting = """
                ____________________________________________________________
                Hello! I'm Jeff
                What can I do for you?
                ____________________________________________________________""";
        System.out.println(greeting);

        boolean loop = true;
        while (loop) {
            String command = sc.nextLine();

            String reply = handleCommand(command);
            System.out.println(reply);

            if (command.equals("bye")) loop = false;
        }

        sc.close();
    }
}
