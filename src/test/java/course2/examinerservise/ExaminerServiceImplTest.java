package course2.examinerservise;


import course2.examinerservise.domain.Question;
import course2.examinerservise.exception.IncorrectQuestionsException;
import course2.examinerservise.service.ExaminerServiceImpl;
import course2.examinerservise.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private Question q1;
    private Question q2;
    private Question q3;
    private Question q4;

    @BeforeEach
    void configureMock() {
        q1 = new Question("q1", "a1");
        q2 = new Question("q2", "a2");
        q3 = new Question("q3", "a3");
        q4 = new Question("q4", "a4");
        Mockito.lenient().when(questionService.getAll()).thenReturn(List.of(q1, q2, q3, q4));
    }

    @Test
    @DisplayName("возвращает нужное количество вопросов")
    void getQuestions_shouldReturnCorrectAmount() {
        Mockito.when(questionService.getRandomQuestion()).thenReturn(q1, q2, q4);
        Collection<Question> result = examinerService.getQuestions(3);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(List.of(q1, q2, q4)));
        Mockito.verify(questionService, Mockito.times(3)).getRandomQuestion();
    }

    @Test
    @DisplayName("выбрасывает исключение, если amount = 0")
    void getQuestions_whenAmountIsZero() {
        assertThrows(IncorrectQuestionsException.class, () -> examinerService.getQuestions(0));
    }

    @Test
    @DisplayName("выбрасывает исключение, если amount больше имеющихся в списке")
    void getQuestions_WhenAmountMoreNumbersOfQuestions() {
        assertThrows(IncorrectQuestionsException.class, () -> examinerService.getQuestions(6));
    }

    @Test
    @DisplayName("выбрасывает исключение, если хранилище пусто")
    void getQuestions_WhenStorageIsEmpty() {
        Mockito.when(questionService.getAll()).thenReturn(List.of());

        assertThrows(IncorrectQuestionsException.class, () -> examinerService.getQuestions(1));
    }

    @Test
    @DisplayName("порверка на дубли")
    void getQuestions_NotReturnDuplicates() {
        Mockito.when(questionService.getRandomQuestion()).thenReturn(q1, q1, q1, q2);
        Collection<Question> result = examinerService.getQuestions(2);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(q1, q2)));
    }
}
