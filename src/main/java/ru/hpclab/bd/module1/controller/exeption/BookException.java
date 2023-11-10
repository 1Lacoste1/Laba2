package ru.hpclab.bd.module1.controller.exeption;

/**
 * Custom exception class for handling book-related exceptions.
 * Extends the RuntimeException class.
 */
public class BookException extends RuntimeException {
    /**
     * Constructs a new BookException with the specified error message.
     * @param message the error message to associate with the exception
     */
    public BookException(final String message) {
        super(message);
    }
}
