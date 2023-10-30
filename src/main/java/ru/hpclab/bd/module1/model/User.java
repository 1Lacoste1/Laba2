package ru.hpclab.bd.module1.model;

import org.springframework.lang.NonNull;
import java.util.UUID;

/**
 * User model.
 */
public class User {
    @NonNull
    private UUID identifier;
    @NonNull
    private String fio;

    /**
     * Builds new user.
     * @param identifier user unique id
     * @param fio user name
     */
    public User(@NonNull final UUID identifier, @NonNull final String fio) {
        this.identifier = identifier;
        this.fio = fio;
    }

    /**
     * Empty contructor.
     */
    public User() {
    }

    /**
     * Returns user's identifier.
     * @return user id
     */
    @NonNull
    public UUID getIdentifier() {
        return identifier;
    }

    /**
     * Sets new identifier for user.
     * @param identifier user id
     */
    public void setIdentifier(@NonNull final UUID identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns user's name.
     * @return user name
     */
    @NonNull
    public String getFio() {
        return fio;
    }

    /**
     * Sets new name for user.
     * @param fio user name
     */
    public void setFio(@NonNull final String fio) {
        this.fio = fio;
    }

    /**
     * Returns string representation of the user.
     */
    @Override
    public String toString() {
        return "User{"
                + "identifier=" + identifier + ", "
                + "fio='" + fio + "'"
                + "}";
    }
}
