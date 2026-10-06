package course2.examinerservise.domain;

import java.util.Objects;

public class Question {
    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Вопрос не задан");
        }
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Ответ не задан");
        }
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question q = (Question) o;
        return Objects.equals(question, q.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }
}
