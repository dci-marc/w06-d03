import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class Exercise {

    public static final String ADMIN_USER = "admin";
    public static final String ADMIN_PWD = "admin";
    public static final String ADMIN_TYPE = "teacher";
    public static final String JANIS_USER = "Janis";
    public static final String JANIS_PWD = "joplin";
    public static final String JANINS_TYPE = "student";


    public static void main (String[] args) {
        Exercise exercise = new Exercise();
        Scanner scanner = new Scanner(System.in);

        // Task 1
        exercise.loginCredentials(scanner);

        // Task 2
        System.out.println("\n\n");
        System.out.println(exercise.isWeekend());
        System.out.println(exercise.isWeekend(LocalDate.of(2021, 8, 6)));
        System.out.println(exercise.isWeekend(LocalDate.of(2021, 8, 7)));
        System.out.println(exercise.isWeekend(LocalDate.of(2021, 8, 8)));
        System.out.println(exercise.isWeekend(LocalDate.of(2021, 8, 9)));

        // Task 3
        System.out.println("\n\n");
        LocalDate date = LocalDate.of(2021, 8, 7);
        exercise.loginCredentialsOpenDoor(scanner, date);
        System.out.println("\n");
        date = LocalDate.of(2021, 8, 6);
        exercise.loginCredentialsOpenDoor(scanner, date);

        // Task 4
        exercise.loginCredentialsMultipleUsers(scanner);
    }

    private void loginCredentials(Scanner scanner) {
        System.out.println("What is your username?");
        String username = scanner.nextLine();

        System.out.println("Type the password for username " + username + ":");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USER) && password.equals(ADMIN_PWD)) {
            System.out.println("Welcome, " + username + "!");
            return;
        }

        System.out.println("Credentials are invalid");
    }

    private boolean isWeekend() {
        LocalDate date = LocalDate.now();

        return isWeekend(date);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    private void loginCredentialsOpenDoor(Scanner scanner, LocalDate date) {
        System.out.println("What is your username?");
        String username = scanner.nextLine();

        System.out.println("Type the password for username " + username + ":");
        String password = scanner.nextLine();

        if ((username.equals(ADMIN_USER) && password.equals(ADMIN_PWD)) || isWeekend(date)) {
            System.out.println("Welcome, " + username + "!");
            return;
        }

        System.out.println("Credentials are invalid");
    }

    private void loginCredentialsMultipleUsers(Scanner scanner) {
        System.out.println("What is your username?");
        String username = scanner.nextLine();

        System.out.println("Type the password for username " + username + ":");
        String password = scanner.nextLine();

        if (!isUserValid(username, password)) {
            System.out.println("An error occurred. You are not authorized.");
        }
    }

    private boolean isUserValid(String username, String password) {
        return switch (username) {
            case ADMIN_USER -> password.equals(ADMIN_PWD);
            case JANIS_USER -> password.equals(JANIS_PWD);
            default -> false;
        };
    }

}
