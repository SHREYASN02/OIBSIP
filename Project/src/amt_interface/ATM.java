package amt_interface;

import java.util.*;
import java.time.LocalDateTime;

class ATM {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, User> users = new HashMap<>();
    private User currentUser;

    public ATM() {
        // Preload with a sample user
        Account sampleAccount = new Account();
        users.put("prabha@123", new User("user123", "9080", sampleAccount));
        users.put("shreyas", new User("user123", "3225", sampleAccount));
    }

    public void start() {
        System.out.println("Welcome to the ATM ");
        handleLogin();
        if (currentUser != null) {
            displayMenu();
        }
    }

    private void handleLogin() {
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        User user = users.get(id);
        if (user != null && user.authenticate(id, pin)) {
            currentUser = user;
            System.out.println("Login Successful");
        } else {
            System.out.println("Invalid credentials");
        }
    }

    private void displayMenu() {
        boolean quit = false;
        while (!quit) {
            System.out.println("\n1. Balance\n2. Transaction History\n3. Withdraw\n4. Deposit\n5. Transfer\n6. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Balance: ₹" + currentUser.getAccount().getBalance());
                    break;
                case 2:
                    for (Transaction t : currentUser.getAccount().getTransactionHistory()) {
                        System.out.println(t);
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    if (currentUser.getAccount().withdraw(amount)) {
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:
                    System.out.print("Enter amount to deposit: ");
                    amount = scanner.nextDouble();
                    currentUser.getAccount().deposit(amount);
                    System.out.println("Deposit successful.");
                    break;
                case 5:
                    System.out.print("Enter recipient User ID: ");
                    String recipientId = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    amount = scanner.nextDouble();
                    User recipient = users.get(recipientId);
                    if (recipient != null) {
                        if (currentUser.getAccount().transfer(recipient.getAccount(), amount)) {
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 6:
                    quit = true;
                    System.out.println("Thank you for using our ATM, Have a Great Day");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}