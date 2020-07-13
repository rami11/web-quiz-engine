package com.rsn.webquizengine.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email already taken")
public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException() {
        this("Email already taken");
    }

    public EmailAlreadyTakenException(String message) {
        super(message);
    }
}
