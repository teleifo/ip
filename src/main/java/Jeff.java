import java.util.Scanner;
import java.util.ArrayList;

public class Jeff {
    private static final ArrayList<Task> list = new ArrayList<>();

    private static boolean isInteger(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String formatReply(String message) {
        return String.format("""
                ____________________________________________________________
                %s
                ____________________________________________________________""", message);
    }

    private static String formatTask(Task t) {
        return String.format("[%s] %s", t.getDoneStatusIcon(), t.getDescription());
    }

    private static String handleCommand(String command) {
        String[] c = command.split(" ");
        switch (c[0]) {
            case "list" -> {
                if (list.isEmpty()) return formatReply("List is currently empty!");

                StringBuilder temp = new StringBuilder();
                temp.append("Here are the tasks in your list:\n");

                for (int i = 1; i <= list.size(); i++) {
                    temp.append(i).append(". ").append(formatTask(list.get(i-1)));
                    if (i != list.size()) temp.append("\n");
                }

                return formatReply(temp.toString());
            }
            case "mark", "unmark" -> {
                if (list.isEmpty()) return formatReply("List is currently empty!");
                if (c.length == 1 || !isInteger(c[1])) return formatReply("You need to provide a valid number!");

                int i = Integer.parseInt(c[1]);
                if (i == 0) return formatReply("You need to provide a valid number!");
                if (i > list.size()) return formatReply("List only has " + list.size() + " item(s)");

                Task t = list.get(i-1);
                t.updateDoneStatus(c[0].equals("mark"));
                return formatReply("Ok, I've marked this task as " + (c[0].equals("mark") ? "" : "not ")
                        + "done: \n" + formatTask(t));
            }
            case "bye" -> {
                return formatReply("Bye. Hope to see you again soon!");
            }
            default -> {
                list.add(new Task(command));
                return formatReply("Added: " + command);
            }
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
