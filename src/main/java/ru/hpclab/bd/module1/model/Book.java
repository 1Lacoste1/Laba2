package ru.hpclab.bd.module1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Entity representing a book.
 * It is automatically registered as a bean in the Spring context due to the {@code @Component} annotation.
 * Lombok annotations are used to generate boilerplate code like getters, setters, and constructors.
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /**
     * The unique identifier of the book.
     */
    @NonNull
    private UUID identifier;

    /**
     * The International Standard Book Number (ISBN) of the book.
     */
    @NonNull
    private long isbn;

    /**
     * The name of the book.
     */
    @NonNull
    private String name;

    /**
     * The author(s) of the book.
     */
    @NonNull
    private String authors;

    /**
     * The year of publication of the book.
     */
    @NonNull
    private int yearPublic;

    /**
     * The number of pages in the book.
     */
    @NonNull
    private int numberPages;
}
