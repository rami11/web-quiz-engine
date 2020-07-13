package com.rsn.webquizengine.quiz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Quiz not found")
public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException() {
    }

    public QuizNotFoundException(String message) {
        super(message);
    }
}
