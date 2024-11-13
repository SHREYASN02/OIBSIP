package online_examination;

import java.util.*;
import java.time.LocalDateTime;
import java.time.Duration;

class QuizSystem {
    private Map<String, User> users = new HashMap<>();
    private User currentUser;
    private Session session;
    private QuestionBank questionBank;

    public QuizSystem() {
        // Initialize question bank and add sample users
        questionBank = new QuestionBank();
        users.put("student1", new Student("student1", "pass123", "Alice"));
        users.put("admin1", new Admin("admin1", "adminpass", "Admin User"));
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Login");
        System.out.println("2. Quit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            login();
            if (currentUser != null) {
                if (currentUser instanceof Admin) {
                    adminMenu();
                } else {
                    studentMenu();
                }
            }
        } else {
            System.out.println("Exiting. Thank you for using the Quiz System.");
        }
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.authenticate(username, password)) {
            currentUser = user;
            System.out.println("Login Successful.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void studentMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("\n1. Start Quiz\n2. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> startQuiz();
                case 2 -> {
                    logout();
                    quit = true;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        Admin admin = (Admin) currentUser;
        boolean quit = false;
        while (!quit) {
            System.out.println("\nAdmin Menu:\n1. Add Question\n2. View Questions\n3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> admin.addQuestion(questionBank);
                case 2 -> questionBank.displayQuestions();
                case 3 -> {
                    logout();
                    quit = true;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void startQuiz() {
        session = new Session(questionBank);
        session.start();
    }

    private void logout() {
        if (session != null) {
            System.out.println("Your Score: " + session.calculateScore() + " out of " + session.getTotalQuestions());
            session = null;
        }
        currentUser = null;
        System.out.println("You have logged out.");
    }
}