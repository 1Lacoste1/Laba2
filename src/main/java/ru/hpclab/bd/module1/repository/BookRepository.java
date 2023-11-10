package ru.hpclab.bd.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.bd.module1.controller.exeption.BookException;
import ru.hpclab.bd.module1.controller.exeption.UserException;
import ru.hpclab.bd.module1.model.Book;

import java.util.*;

import static java.lang.String.format;

/**
 * Repository class for managing books in a mock database.
 * Supports basic CRUD operations and stores books in an in-memory map.
 */
@Repository
public class BookRepository {
    public static final String BOOK_NOT_FOUND_MSG = "Book with ID %s not found";

    public static final String BOOKS_NOT_FOUND_MSG = "Books not found";

    public static final String BOOK_EXISTS_MSG = "Book with ID %s is already exists";

    private final Map<UUID, Book> books = new HashMap<>();

    /**
     * Finds and returns all books.
     * @return a list of all books
     */
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    /**
     * Finds a book by its identifier.
     * @param id the UUID of the book to find
     * @return the found book
     * @throws UserException if the book is not found
     */
    public Book findById(final UUID id) {
        final var book = books.get(id);
        if (book == null) {
            throw new BookException(format(BOOK_NOT_FOUND_MSG, id));
        }
        return book;
    }

    /**
     * Deletes a book by its identifier.
     * @param id the UUID of the book to delete
     * @throws UserException if the book is not found
     */
    public void delete(final UUID id) {
        final var removed = books.remove(id);
        if (removed == null) {
            throw new BookException(format(BOOK_NOT_FOUND_MSG, id));
        }
    }

    /**
     * Saves a book. If the book's identifier is not set, it will generate a new UUID.
     * @param book the book to save
     * @return the saved book with an identifier
     * @throws UserException if the book already exists
     */
    public Book save(final Book book) {
        if (ObjectUtils.isEmpty(book.getIdentifier())) {
            book.setIdentifier(UUID.randomUUID());
        }

        final var bookData = books.get(book.getIdentifier());
        if (bookData != null) {
            throw new BookException(format(BOOK_EXISTS_MSG, book.getIdentifier()));
        }

        books.put(book.getIdentifier(), book);

        return book;
    }

    /**
     * Updates an existing book's details.
     * @param book the book with updated details
     * @return the updated book
     * @throws UserException if the book is not found
     */
    public Book put(final Book book) {
        final var bookData = books.get(book.getIdentifier());
        if (bookData == null) {
            throw new BookException(format(BOOK_NOT_FOUND_MSG, book.getIdentifier()));
        }

        final var removed = books.remove(book.getIdentifier());
        if (removed != null) {
            books.put(book.getIdentifier(), book);
        } else {
            throw new BookException(format(BOOK_NOT_FOUND_MSG, book.getIdentifier()));
        }

        return book;
    }

    /**
     * Clears all books from the repository.
     */
    public void clear() {
        books.clear();
    }
}
