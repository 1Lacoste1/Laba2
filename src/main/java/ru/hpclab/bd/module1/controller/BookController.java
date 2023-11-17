package ru.hpclab.bd.module1.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.bd.module1.model.Book;
import ru.hpclab.bd.module1.service.BookService;

import java.util.List;

/**
 * REST controller for managing book-related operations.
 * Handles the web requests and invokes service methods to perform CRUD operations on books.
 */
@RestController
@RequestMapping("/books")
@Data
public class BookController {
    private final BookService bookService;

    /**
     * Retrieves a list of all books.
     * @return a list of {@link Book}
     */
    @GetMapping()
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Retrieves a single book by its ID.
     * @param id the ID of the book to retrieve
     * @return the {@link Book} with the specified ID
     */
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable final String id) {
        return bookService.getBookById(id);
    }

    /**
     * Deletes a book by its ID.
     * @param id the ID of the book to delete
     */
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable final String id) {
        bookService.deleteBook(id);
    }

    /**
     * Saves a new book.
     * @param book the {@link Book} to save
     * @return the saved {@link Book}
     */
    @PostMapping()
    public Book saveBook(@RequestBody final Book book) {
        return bookService.saveBook(book);
    }

    /**
     * Updates an existing book.
     * @param id   the ID of the book to update
     * @param book the {@link Book} with updated fields
     * @return the updated {@link Book}
     */
    @PutMapping(value = "/{id}")
    public Book updateBook(
            @PathVariable(required = false) final String id,
            @RequestBody final Book book
    ) {
        return bookService.updateBook(id, book);
    }
}
