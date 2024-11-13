package online_examination;

class MCQ {
    private String question;
    private String[] options;
    private int correctOptionIndex;

    public MCQ(String question, String[] options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public boolean checkAnswer(int userAnswerIndex) {
        return userAnswerIndex == correctOptionIndex;
    }

    public String displayQuestion() {
        StringBuilder sb = new StringBuilder();
        sb.append(question).append("\n");
        for (int i = 0; i < options.length; i++) {
            sb.append((i + 1)).append(". ").append(options[i]).append("\n");
        }
        return sb.toString();
    }
}
