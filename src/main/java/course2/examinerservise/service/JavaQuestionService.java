package course2.examinerservise.service;

import course2.examinerservise.domain.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Вопрос пуст");
        }
        questions.add(question);
        return question;
    }
    @Override
    public Question remove(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Вопрос пуст");
        }
        boolean removed = questions.remove(question);  //возвращает true, если элемент был удалён
        return removed ? question : null;   // если удалили(true) - вернет удалённый вопрос, если нет - null
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Хранилище вопросов пусто");
        }
        List<Question> questionList = new ArrayList<>(questions);
        return questionList.get(random.nextInt(questionList.size()));
    }
}
