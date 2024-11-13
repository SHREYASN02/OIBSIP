package online_examination;

import java.util.Scanner;

class Admin extends User {
    public Admin(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    public void addQuestion(QuestionBank questionBank) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter question: ");
        String questionText = scanner.nextLine();
        
        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            options[i] = scanner.nextLine();
        }
        
        System.out.print("Enter the correct option number (1-4): ");
        int correctOption = scanner.nextInt() - 1;

        questionBank.addQuestion(new MCQ(questionText, options, correctOption));
        System.out.println("Question added successfully.");
    }
}