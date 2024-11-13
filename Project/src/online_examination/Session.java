package online_examination;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Session {
    private List<MCQ> questions;
    private Map<MCQ, Integer> userAnswers = new HashMap<>();
    private final int durationInSeconds = 300;
    private LocalDateTime startTime;

    public Session(QuestionBank questionBank) {
        questions = questionBank.getQuestions();
    }

    public void start() {
        startTime = LocalDateTime.now();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quiz started. You have 5 minutes to complete it.");

        for (MCQ question : questions) {
            if (isTimeUp()) {
                autoSubmit();
                System.out.println("Time's up! Answers submitted automatically.");
                return;
            }
            System.out.println(question.displayQuestion());
            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt() - 1;
            userAnswers.put(question, answer);
        }

        System.out.println("Quiz completed. Submitting your answers.");
    }

    private boolean isTimeUp() {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(startTime, now).getSeconds() >= durationInSeconds;
    }

    private void autoSubmit() {
        System.out.println("Submitting answers automatically...");
    }

    public int calculateScore() {
        int score = 0;
        for (Map.Entry<MCQ, Integer> entry : userAnswers.entrySet()) {
            if (entry.getKey().checkAnswer(entry.getValue())) {
                score++;
            }
        }
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
