package course2.examinerservise.controller;


import course2.examinerservise.exception.IncorrectQuestionsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuestionControllerAdvice {
    @ExceptionHandler(IncorrectQuestionsException.class)
    public ResponseEntity<String> handleNoSuchProduct(IncorrectQuestionsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
