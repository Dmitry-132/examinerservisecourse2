package course2.examinerservise;

import course2.examinerservise.domain.Question;
import course2.examinerservise.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTests {

    private JavaQuestionService service;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService();
    }

    @Test
    @DisplayName("add(String, String)")
    void add_whenTwoStrings() {
        Question result = service.add("Вопрос", "Ответ");

        assertNotNull(result);
        assertEquals(1, service.getAll().size());
        assertEquals("Вопрос", result.getQuestion());
        assertEquals("Ответ", result.getAnswer());
    }

    @Test
    @DisplayName("add + дубль")
    void add_withDouble() {
        service.add("q2", "a2");
        service.add("q2", "a2");
        service.add("q3", "a3");
        service.add("q3", "a44");

        assertNotNull(service);
        assertEquals(2, service.getAll().size());
    }

    @Test
    @DisplayName("add с пустой строкой")
    void add_whenTwoStringsAndNull() {
        assertThrows(IllegalArgumentException.class, () -> service.add("Вопрос", ""));
        assertThrows(IllegalArgumentException.class, () -> service.add("", "Ответ"));
        assertThrows(IllegalArgumentException.class, () -> service.add("", ""));
        assertThrows(IllegalArgumentException.class, () -> service.add(null));
    }

    @Test
    @DisplayName("remove удаляет существующий вопрос")
    void remove_whenQuestionExists() {
        Question question = service.add("q1", "a1");
        Question removed = service.remove(question);

        assertNotNull(removed);
        assertTrue(service.getAll().isEmpty());
        assertEquals(question, removed);
    }

    @Test
    @DisplayName("remove возвращает null, если вопроса нет в базе")
    void remove_whenQuestionDoesNotExist() {
        Question question = new Question("q1", "a1");
        Question removed = service.remove(question);

        assertNull(removed);
    }

    @Test
    @DisplayName("getAll() возвращает добавленные вопросы")
    void getAll_returnQuestions() {
        service.add("q1", "a1");
        service.add("q2", "a2");
        service.add("q3", "a3");
        Collection<Question> all = service.getAll();

        assertEquals(3, all.size());
    }

    @Test
    @DisplayName("getAll() возвращает пустую коллекцию")
    void getAll_whenAbsenceOfQuestions() {
        Collection<Question> all = service.getAll();

        assertNotNull(all);
        assertTrue(all.isEmpty());
    }

    @Test
    @DisplayName("getRandomQuestion() выбросает исключение, когда хранилище пусто")
    void getRandomQuestion_whenEmpty() {
        assertThrows(IllegalStateException.class, () -> service.getRandomQuestion());
    }

    @Test
    @DisplayName("getRandomQuestion() возвращает добавленный вопрос")
    void getRandomQuestion_whenNotEmpty() {
        Question question = new Question("q1", "a1");
        service.add(question);
        Question random = service.getRandomQuestion();

        assertEquals(question, random);
    }
}
