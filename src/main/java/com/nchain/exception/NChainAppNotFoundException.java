package com.nchain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NChainAppNotFoundException extends RuntimeException {

    public NChainAppNotFoundException(String message) {
        super(message);
    }

    public NChainAppNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
