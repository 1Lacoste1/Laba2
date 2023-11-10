package ru.hpclab.bd.module1.controller.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling BookException in Spring MVC controllers.
 * This class is annotated with `@ControllerAdvice`, indicating that it is intended to be
 * used as an exception handler across multiple controllers.
 */
@ControllerAdvice
public class BookExceptionHandler {
    /**
     * Exception handler method for handling BookException.
     * It returns a ResponseEntity with a BAD_REQUEST status and the exception message as the response body.
     * @param e the BookException to handle
     * @return a ResponseEntity with a BAD_REQUEST status and the exception message as the response body
     */
    @ExceptionHandler
    public ResponseEntity<String> onBookException(final BookException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
