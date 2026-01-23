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

    private static String handleInput(String input) {
        if (input.equals("list") || input.startsWith("list ")) {
            if (list.isEmpty()) return formatReply("List is currently empty!");

            StringBuilder temp = new StringBuilder();
            temp.append("Here are the tasks in your list:\n");

            for (int i = 1; i <= list.size(); i++) {
                temp.append(i).append(". ").append(list.get(i-1));
                if (i != list.size()) temp.append("\n");
            }

            return formatReply(temp.toString());
        }

        if (input.equals("mark") || input.startsWith("mark ")) {
            if (list.isEmpty()) return formatReply("List is currently empty!");

            String numStr = "";
            if (input.length() > 4) numStr = input.substring(4).trim();
            if (!isInteger(numStr)) return formatReply("You need to provide task ID!\nFormat: mark [task id]");

            int num = Integer.parseInt(numStr);
            if (num == 0) return formatReply("Task ID needs to be a valid number!\nFormat: mark [task id]");
            if (num > list.size()) return formatReply("List only has " + list.size() + " item(s)");

            Task t = list.get(Integer.parseInt(numStr)-1);
            t.updateDoneStatus(true);

            return formatReply("Ok, I've marked this task as done: \n" + t);
        }

        if (input.equals("unmark") || input.startsWith("unmark ")) {
            if (list.isEmpty()) return formatReply("List is currently empty!");

            String numStr = "";
            if (input.length() > 6) numStr = input.substring(6).trim();
            if (!isInteger(numStr)) return formatReply("You need to provide task ID!\nFormat: unmark [task id]");

            int num = Integer.parseInt(numStr);
            if (num == 0) return formatReply("Task ID needs to be a valid number!\nFormat: unmark [task id]");
            if (num > list.size()) return formatReply("List only has " + list.size() + " item(s)");

            Task t = list.get(Integer.parseInt(numStr)-1);
            t.updateDoneStatus(false);

            return formatReply("Ok, I've marked this task as not done: \n" + t);
        }

        if (input.equals("todo") || input.startsWith("todo ")) {
            String description = "";

            if (input.length() > 4) description = input.substring(4).trim();
            if (description.isEmpty()) return formatReply("ToDo description cannot be empty.\nFormat: todo [description]");

            Task t = new ToDo(description);
            list.add(t);
            return formatReply("Ok, I've added a ToDo task:\n" + t
                    + "\nThere " + ((list.size() > 1) ? "are " : "is ") + list.size() + " task(s) in the list.");
        }

        if (input.equals("deadline") || input.startsWith("deadline ")) {
            if (!input.contains(" /by")) {
                return formatReply("Deadline must have /by.\nFormat: deadline [description] /by [due by]");
            }

            String[] parts = input.split(" /by", 2);

            String description = parts[0].substring(8).trim();
            String by = parts[1].trim();

            if (description.isEmpty()) return formatReply("Deadline description cannot be empty.\nFormat: deadline [description] /by [due by]");
            if (by.isEmpty()) return formatReply("Deadline due by cannot be empty.\nFormat: deadline [description] /by [due by]");

            Task t = new Deadline(description, by);
            list.add(t);
            return formatReply("Ok, I've added a Deadline task:\n" + t
                    + "\nThere " + ((list.size() > 1) ? "are " : "is ") + list.size() + " task(s) in the list.");
        }

        if (input.equals("event") || input.startsWith("event ")) {
            if (!input.contains(" /from") || !input.contains(" /to")) {
                return formatReply("Event must have /from and /to.\nFormat: event [description] /from [from] /to [to]");
            }

            String[] firstSplit = input.split(" /from", 2);
            String description = firstSplit[0].substring(5).trim();

            String[] secondSplit = firstSplit[1].split(" /to", 2);
            String from = secondSplit[0].trim();
            String to = secondSplit[1].trim();

            if (description.isEmpty()) return formatReply("Event description cannot be empty.\nFormat: event [description] /from [from] /to [to]");
            if (from.isEmpty()) return formatReply("Event from cannot be empty.\nFormat: event [description] /from [from] /to [to]");
            if (to.isEmpty()) return formatReply("Event to cannot be empty.\nFormat: event [description] /from [from] /to [to]");

            Task t = new Event(description, from, to);
            list.add(t);
            return formatReply("Ok, I've added an Event task:\n" + t
                    + "\nThere " + ((list.size() > 1) ? "are " : "is ") + list.size() + " task(s) in the list.");
        }

        if (input.equals("bye") || input.startsWith("bye ")) return formatReply("Bye. Hope to see you again soon!");

        return formatReply("Sorry, I don't know what that means!");
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
            String input = sc.nextLine();

            String reply = handleInput(input);
            System.out.println(reply);

            if (input.equals("bye") || input.startsWith("bye ")) loop = false;
        }

        sc.close();
    }
}
