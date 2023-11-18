package ru.hpclab.bd.module1.controller.exeption;

/**
 * Custom exception class for handling book-related exceptions in the application.
 */
public class BookException extends RuntimeException {

    /**
     * Constructs a new BookException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public BookException(final String message) {
        super(message);
    }
}
