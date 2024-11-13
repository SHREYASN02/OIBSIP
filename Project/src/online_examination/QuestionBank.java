package online_examination;

import java.util.ArrayList;
import java.util.List;

class QuestionBank {
    private List<MCQ> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
    }

    public void addQuestion(MCQ question) {
        questions.add(question);
    }

    public List<MCQ> getQuestions() {
        return questions;
    }

    public void displayQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).displayQuestion());
        }
    }
}