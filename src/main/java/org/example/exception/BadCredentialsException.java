package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class BadCredentialsException extends IllegalStateException{
    public BadCredentialsException() {
    }

    public BadCredentialsException(String s) {
        super(s);
    }

    public BadCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCredentialsException(Throwable cause) {
        super(cause);
    }
}
