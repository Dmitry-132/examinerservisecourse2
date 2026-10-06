package course2.examinerservise.service;

import course2.examinerservise.domain.Question;
import course2.examinerservise.exception.IncorrectQuestionsException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0) {
            throw new IncorrectQuestionsException("Количество вопросов должно быть больше 0");
        }
        if (amount > questionService.getAll().size()) {
            throw new IncorrectQuestionsException("Запрошено " + amount + " вопросов, доступно" + questionService.getAll().size());
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
