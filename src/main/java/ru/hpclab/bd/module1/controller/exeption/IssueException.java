package ru.hpclab.bd.module1.controller.exeption;

/**
 * Custom exception class for handling issue-related exceptions in the application.
 */
public class IssueException extends RuntimeException {

    /**
     * Constructs a new IssueException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public IssueException(final String message) {
        super(message);
    }
}
