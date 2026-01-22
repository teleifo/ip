import java.util.Scanner;

public class Jeff {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Jeff\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(greeting);

        boolean loop = true;
        while (loop) {
            String command = sc.nextLine();
            String reply = "";

            if (command.equals("bye")) {
                loop = false;
                reply = "____________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________";
            } else {
                reply = "____________________________________________________________\n"
                        + command + "\n"
                        + "____________________________________________________________";
            }

            System.out.println(reply);
        }

        sc.close();
    }
}
