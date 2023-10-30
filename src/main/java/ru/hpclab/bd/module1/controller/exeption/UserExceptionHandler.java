package ru.hpclab.bd.module1.controller.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Basic handler for UserException.
 */
@ControllerAdvice
public class UserExceptionHandler {

    /**
     * Handles UserException and returns BAD_REQUEST.
     * @param e user exception
     * @return response with exception message and BAD_REQUEST status code
     */
    @ExceptionHandler
    public ResponseEntity<String> onUserException(final UserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
