package ru.hpclab.bd.module1.controller.exeption;

/**
 * Custom exception class for handling user-related exceptions in the application.
 */
public class UserException extends RuntimeException {

    /**
     * Constructs a new UserException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public UserException(final String message) {
        super(message);
    }
}
