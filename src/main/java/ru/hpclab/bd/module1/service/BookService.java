package ru.hpclab.bd.module1.service;

import lombok.Data;
import ru.hpclab.bd.module1.model.Book;
import ru.hpclab.bd.module1.repository.BookRepository;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing books.
 * It encapsulates the logic for accessing and manipulating book data, delegating to {@link BookRepository}.
 */
@Data
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Retrieves all books from the repository.
     * @return a list of all books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its identifier.
     * @param id the identifier of the book to retrieve
     * @return the book with the specified identifier, if found
     */
    public Book getBookById(final String id) {
        return bookRepository.findById(UUID.fromString(id));
    }

    /**
     * Saves the given book entity to the repository.
     * @param book the book to save
     * @return the saved book entity
     */
    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    /**
     * Updates the book entity with the given identifier.
     * @param id   the identifier of the book to update
     * @param book the new book data to update
     * @return the updated book entity
     */
    public Book updateBook(final String id, final Book book) {
        book.setIdentifier(UUID.fromString(id));
        return bookRepository.put(book);
    }

    /**
     * Deletes the book with the specified identifier from the repository.
     *
     * @param id the identifier of the book to delete
     */
    public void deleteBook(final String id) {
        bookRepository.delete(UUID.fromString(id));
    }
}
