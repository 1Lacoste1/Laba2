package ru.hpclab.bd.module1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Model class representing an issue of a book to a user.
 * This class holds information about the unique identifier, the user, the book, issue date, and usage term in days.
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    /**
     * The unique identifier for the issue.
     */
    @NonNull
    private UUID id;

    /**
     * The user to whom the book is issued.
     */
    @NonNull
    private User user;

    /**
     * The book that is issued to the user.
     */
    @NonNull
    private Book book;

    /**
     * The date on which the book was issued.
     */
    @NonNull
    private LocalDate issueDate;

    /**
     * The number of days for which the book is issued to the user.
     */
    @NonNull
    private int usageTermDays;
}