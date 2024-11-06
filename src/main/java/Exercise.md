I’ll go step-by-step through what each function does, so you can understand how they work and why they’re written this way.

### Overview

This Java program contains functions that simulate a basic user login system. It checks credentials (username and password) and verifies if today or a specific date is a weekend. Let’s go through each part of the code in detail.

### 1. The `main` Method

```java
public static void main(String[] args) {
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
```

The `main` method is the entry point of any Java application. Here’s what each line of this method does:

- **`Exercise exercise = new Exercise();`**: Creates an instance of the `Exercise` class. This allows us to call the non-static methods in the class (like `loginCredentials`, `isWeekend`, etc.).
- **`Scanner scanner = new Scanner(System.in);`**: Sets up a scanner to read input from the user. This lets the program take usernames and passwords from the keyboard.

Each task in the `main` method demonstrates different functions of the `Exercise` class:
1. **Task 1**: Calls `loginCredentials(scanner)` to ask for login credentials and verify them.
2. **Task 2**: Checks if today or specific dates are weekends by calling `isWeekend`.
3. **Task 3**: Combines login checking and weekend checking with `loginCredentialsOpenDoor(scanner, date)`.
4. **Task 4**: Demonstrates login checking for multiple users with `loginCredentialsMultipleUsers(scanner)`.

### 2. `loginCredentials`

```java
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
```

This method checks if the user has entered the correct credentials for the admin account:

- **`scanner.nextLine()`**: Reads a line of text (the username or password) entered by the user.
- **`if (username.equals(ADMIN_USER) && password.equals(ADMIN_PWD))`**: Checks if the entered username and password match the predefined admin credentials. If they do, it prints "Welcome" with the username. If not, it prints "Credentials are invalid".

### 3. `isWeekend` (without parameters)

```java
private boolean isWeekend() {
    LocalDate date = LocalDate.now();

    return isWeekend(date);
}
```

This method checks if today is a weekend:

- **`LocalDate.now()`**: Gets today’s date.
- **`isWeekend(date)`**: Calls the other `isWeekend` method (explained next) to determine if today is a weekend.

### 4. `isWeekend` (with a `LocalDate` parameter)

```java
private boolean isWeekend(LocalDate date) {
    return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
}
```

This method checks if a specific date falls on a weekend:

- **`date.getDayOfWeek()`**: Returns the day of the week for the date.
- **`equals(DayOfWeek.SATURDAY) || equals(DayOfWeek.SUNDAY)`**: Checks if the day of the week is Saturday or Sunday. If so, it returns `true`, meaning it’s a weekend; otherwise, it returns `false`.

### 5. `loginCredentialsOpenDoor`

```java
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
```

This method combines login checking with a date check:

- **`if ((username.equals(ADMIN_USER) && password.equals(ADMIN_PWD)) || isWeekend(date))`**: The method allows login either if the username and password are correct, or if it’s a weekend (determined by `isWeekend(date)`). If either condition is true, the user is welcomed; otherwise, it shows "Credentials are invalid".

### 6. `loginCredentialsMultipleUsers`

```java
private void loginCredentialsMultipleUsers(Scanner scanner) {
    System.out.println("What is your username?");
    String username = scanner.nextLine();

    System.out.println("Type the password for username " + username + ":");
    String password = scanner.nextLine();

    if (!isUserValid(username, password)) {
        System.out.println("An error occurred. You are not authorized.");
    }
}
```

This method checks the credentials of multiple users (admin and Janis):

- **`if (!isUserValid(username, password))`**: Calls `isUserValid(username, password)` to check if the credentials are correct. If they’re not, it prints "An error occurred."

### 7. `isUserValid`

```java
private boolean isUserValid(String username, String password) {
    return switch (username) {
        case ADMIN_USER -> password.equals(ADMIN_PWD);
        case JANIS_USER -> password.equals(JANIS_PWD);
        default -> false;
    };
}
```

This method uses a `switch` statement to check if the username and password match either the admin or Janis:

- **`switch (username)`**: Compares the `username` against predefined cases (`ADMIN_USER` and `JANIS_USER`).
- **`case ADMIN_USER -> password.equals(ADMIN_PWD);`**: If the username matches `ADMIN_USER`, it checks if the password matches `ADMIN_PWD`.
- **`case JANIS_USER -> password.equals(JANIS_PWD);`**: Similarly, it checks if `JANIS_USER` has the password `JANIS_PWD`.
- **`default -> false;`**: If the username is neither admin nor Janis, it returns `false`, indicating the credentials are invalid.