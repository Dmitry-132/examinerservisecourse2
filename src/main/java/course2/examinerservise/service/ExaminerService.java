package course2.examinerservise.service;

import course2.examinerservise.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);


}
