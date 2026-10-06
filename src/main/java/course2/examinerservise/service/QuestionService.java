package course2.examinerservise.service;

import course2.examinerservise.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;


public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

}
