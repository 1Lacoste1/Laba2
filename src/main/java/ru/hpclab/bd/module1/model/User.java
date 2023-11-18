package ru.hpclab.bd.module1.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A model class representing a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * The unique identifier of the user.
     */
    private Long id;

    /**
     * The full name of the user.
     */
    private String fullName;

    /**
     * The date of birth of the user.
     */
    private LocalDate birthDate;

    /**
     * The library card number associated with the user.
     */
    private String libraryCardNumber;

    /**
     * A set of issues associated with this user.
     */
    private Set<Issue> issues = new HashSet<>();
}
