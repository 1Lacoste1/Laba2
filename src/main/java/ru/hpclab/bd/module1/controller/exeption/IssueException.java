package ru.hpclab.bd.module1.controller.exeption;

/**
 * Custom exception class for handling issue-related exceptions.
 * Extends the RuntimeException class.
 */
public class IssueException extends RuntimeException {
    /**
     * Constructs a new IssueException with the specified error message.
     * @param message the error message to associate with the exception
     */
    public IssueException(final String message) {
        super(message);
    }
}
