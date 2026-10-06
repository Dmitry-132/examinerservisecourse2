package course2.examinerservise.controller;


import course2.examinerservise.domain.Question;
import course2.examinerservise.service.ExaminerService;
import course2.examinerservise.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/add") //http://localhost:8080/exam/java/add?question="вопрос"?&answer="ответ"
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAll();
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }
}
