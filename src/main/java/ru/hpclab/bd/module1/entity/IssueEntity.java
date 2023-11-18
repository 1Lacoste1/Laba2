package ru.hpclab.bd.module1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing information about an issue.
 */
@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "isbn", nullable = false)
    private BookEntity book;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "usage_period", nullable = false)
    private Integer usagePeriod;

    /**
     * Get the ID of the issue.
     *
     * @return The ID of the issue.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the issue.
     *
     * @param id The ID to set.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Get the user associated with the issue.
     *
     * @return The user associated with the issue.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Set the user associated with the issue.
     *
     * @param user The user to set.
     */
    public void setUser(final UserEntity user) {
        this.user = user;
    }

    /**
     * Get the book associated with the issue.
     *
     * @return The book associated with the issue.
     */
    public BookEntity getBook() {
        return book;
    }

    /**
     * Set the book associated with the issue.
     *
     * @param book The book to set.
     */
    public void setBook(final BookEntity book) {
        this.book = book;
    }

    /**
     * Get the issue date of the issue.
     *
     * @return The issue date of the issue.
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Set the issue date of the issue.
     *
     * @param issueDate The issue date to set.
     */
    public void setIssueDate(final LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Get the usage period of the issue.
     *
     * @return The usage period of the issue.
     */
    public Integer getUsagePeriod() {
        return usagePeriod;
    }

    /**
     * Set the usage period of the issue.
     *
     * @param usagePeriod The usage period to set.
     */
    public void setUsagePeriod(final Integer usagePeriod) {
        this.usagePeriod = usagePeriod;
    }
}
