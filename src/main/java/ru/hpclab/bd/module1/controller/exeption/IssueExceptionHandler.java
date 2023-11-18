package ru.hpclab.bd.module1.controller.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling IssueException in the application.
 */
@ControllerAdvice
public class IssueExceptionHandler {

    /**
     * Handles exceptions of type IssueException and returns a ResponseEntity with a Bad Request status
     * and the error message from the exception.
     *
     * @param e The IssueException to be handled.
     * @return ResponseEntity with a Bad Request status and the error message.
     */
    @ExceptionHandler
    public ResponseEntity<String> onIssueException(final IssueException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
