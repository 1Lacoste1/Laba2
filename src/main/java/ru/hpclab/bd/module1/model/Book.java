package ru.hpclab.bd.module1.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * A model class representing a book.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /**
     * The ISBN (International Standard Book Number) of the book.
     */
    private String isbn;

    /**
     * The title of the book.
     */
    private String title;

    /**
     * A list of authors of the book.
     */
    private String authorList;

    /**
     * The year of publication of the book.
     */
    private Integer publicationYear;

    /**
     * The page count of the book.
     */
    private Integer pageCount;

    /**
     * A set of issues associated with this book.
     */
    private Set<Issue> issues = new HashSet<>();
}
