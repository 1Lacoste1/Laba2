package ru.hpclab.bd.module1.controller.exeption;

/**
 * Generic exception related to users resource.
 */
public class UserException extends RuntimeException {
    /**
     * Builds new UserException from the given message.
     * @param message exception message
     */
    public UserException(final String message) {
        super(message);
    }
}
