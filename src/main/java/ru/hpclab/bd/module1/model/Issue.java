package ru.hpclab.bd.module1.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * A model class representing an issue.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    /**
     * The unique identifier of the issue.
     */
    private Long id;

    /**
     * The user associated with the issue.
     */
    private User user;

    /**
     * The book associated with the issue.
     */
    private Book book;

    /**
     * The date on which the issue was made.
     */
    private LocalDate issueDate;

    /**
     * The period for which the book is issued, in days.
     */
    private Integer usagePeriod;
}
