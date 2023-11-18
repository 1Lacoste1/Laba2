package ru.hpclab.bd.module1.controller.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling BookException in the application.
 */
@ControllerAdvice
public class BookExceptionHandler {

    /**
     * Handles exceptions of type BookException and returns a ResponseEntity with a Bad Request status
     * and the error message from the exception.
     *
     * @param e The BookException to be handled.
     * @return ResponseEntity with a Bad Request status and the error message.
     */
    @ExceptionHandler
    public ResponseEntity<String> onBookException(final BookException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
