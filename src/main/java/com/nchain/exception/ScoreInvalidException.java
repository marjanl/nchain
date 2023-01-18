package com.nchain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ScoreInvalidException extends RuntimeException {

    public ScoreInvalidException(String message) {
        super(message);
    }

    public ScoreInvalidException(String message, Throwable cause){
        super(message, cause);
    }
}
