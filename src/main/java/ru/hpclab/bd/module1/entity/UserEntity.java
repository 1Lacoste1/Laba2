package ru.hpclab.bd.module1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing user information.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "library_card_number", nullable = false, unique = true)
    private String libraryCardNumber;

    @OneToMany(mappedBy = "user")
    private Set<IssueEntity> issues = new HashSet<>();

    /**
     * Get the ID of the user.
     *
     * @return The ID of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the user.
     *
     * @param id The ID to set.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Get the full name of the user.
     *
     * @return The full name of the user.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the full name of the user.
     *
     * @param fullName The full name to set.
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /**
     * Get the birth date of the user.
     *
     * @return The birth date of the user.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Set the birth date of the user.
     *
     * @param birthDate The birth date to set.
     */
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get the library card number of the user.
     *
     * @return The library card number of the user.
     */
    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    /**
     * Set the library card number of the user.
     *
     * @param libraryCardNumber The library card number to set.
     */
    public void setLibraryCardNumber(final String libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

    /**
     * Get the set of issues associated with the user.
     *
     * @return The set of issues associated with the user.
     */
    public Set<IssueEntity> getIssues() {
        return issues;
    }

    /**
     * Set the set of issues associated with the user.
     *
     * @param issues The set of issues to set.
     */
    public void setIssues(final Set<IssueEntity> issues) {
        this.issues = issues;
    }
}
