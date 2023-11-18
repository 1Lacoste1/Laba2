package ru.hpclab.bd.module1.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hpclab.bd.module1.controller.exeption.BookException;
import ru.hpclab.bd.module1.entity.BookEntity;
import ru.hpclab.bd.module1.repository.BookRepository;

import java.util.List;

import static java.lang.String.format;

/**
 * Service class for managing books.
 */
@Service
@Data
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Error message used to indicate that a book with the specified ISBN was not found.
     *
     * @param %s Placeholder where a specific book's ISBN will be inserted.
     */
    public static final String BOOK_NOT_FOUND_MSG = "Book with ISBN %s not found";

    /**
     * Constructor for the BookService class.
     *
     * @param bookRepository Repository for accessing book data.
     */
    @Autowired
    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Get a list of all books.
     *
     * @return List of BookEntity representing all books.
     */
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Get a book by its ISBN.
     *
     * @param isbn The ISBN of the book to retrieve.
     * @return The BookEntity representing the book.
     * @throws BookException If the book is not found.
     */
    public BookEntity getBookByIsbn(final String isbn) {
        return bookRepository.findById(Long.valueOf(isbn))
                .orElseThrow(() -> new BookException(format(BOOK_NOT_FOUND_MSG, isbn)));
    }

    /**
     * Save a new book.
     *
     * @param bookEntity The BookEntity to save.
     * @return The saved BookEntity.
     */
    @Transactional
    public BookEntity saveBook(final BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    /**
     * Update an existing book.
     *
     * @param isbn       The ISBN of the book to update.
     * @param bookEntity The updated BookEntity.
     * @return The updated BookEntity.
     */
    @Transactional
    public BookEntity updateBook(final String isbn, final BookEntity bookEntity) {
        BookEntity existingBook = getBookByIsbn(isbn);
        existingBook.setTitle(bookEntity.getTitle());
        existingBook.setAuthorList(bookEntity.getAuthorList());
        existingBook.setPublicationYear(bookEntity.getPublicationYear());
        existingBook.setPageCount(bookEntity.getPageCount());
        return bookRepository.save(existingBook);
    }

    /**
     * Delete a book by its ISBN.
     *
     * @param isbn The ISBN of the book to delete.
     * @throws BookException If the book is not found.
     */
    @Transactional
    public void deleteBook(final String isbn) {
        if (!bookRepository.existsById(Long.valueOf(isbn))) {
            throw new BookException(format(BOOK_NOT_FOUND_MSG, isbn));
        }
        bookRepository.deleteById(Long.valueOf(isbn));
    }
}
