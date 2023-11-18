package ru.hpclab.bd.module1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing information about a book.
 */
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author_list", nullable = false)
    private String authorList;

    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;

    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @OneToMany(mappedBy = "book")
    private Set<IssueEntity> issues = new HashSet<>();

    /**
     * Get the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set the ISBN of the book.
     *
     * @param isbn The ISBN to set.
     */
    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the book.
     *
     * @param title The title to set.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Get the author list of the book.
     *
     * @return The author list of the book.
     */
    public String getAuthorList() {
        return authorList;
    }

    /**
     * Set the author list of the book.
     *
     * @param authorList The author list to set.
     */
    public void setAuthorList(final String authorList) {
        this.authorList = authorList;
    }

    /**
     * Get the publication year of the book.
     *
     * @return The publication year of the book.
     */
    public Integer getPublicationYear() {
        return publicationYear;
    }

    /**
     * Set the publication year of the book.
     *
     * @param publicationYear The publication year to set.
     */
    public void setPublicationYear(final Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Get the page count of the book.
     *
     * @return The page count of the book.
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * Set the page count of the book.
     *
     * @param pageCount The page count to set.
     */
    public void setPageCount(final Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Get the set of issues associated with the book.
     *
     * @return The set of issues associated with the book.
     */
    public Set<IssueEntity> getIssues() {
        return issues;
    }

    /**
     * Set the set of issues associated with the book.
     *
     * @param issues The set of issues to set.
     */
    public void setIssues(final Set<IssueEntity> issues) {
        this.issues = issues;
    }
}
