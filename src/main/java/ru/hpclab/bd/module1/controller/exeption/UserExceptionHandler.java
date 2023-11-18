package ru.hpclab.bd.module1.controller.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling UserException in the application.
 */
@ControllerAdvice
public class UserExceptionHandler {

    /**
     * Handles exceptions of type UserException and returns a ResponseEntity with a Bad Request status
     * and the error message from the exception.
     *
     * @param e The UserException to be handled.
     * @return ResponseEntity with a Bad Request status and the error message.
     */
    @ExceptionHandler
    public ResponseEntity<String> onUserException(final UserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
