package ru.hpclab.bd.module1.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.bd.module1.entity.BookEntity;
import ru.hpclab.bd.module1.mapper.BookMapper;
import ru.hpclab.bd.module1.model.Book;
import ru.hpclab.bd.module1.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for handling book-related HTTP requests.
 */
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    /**
     * Constructor for creating an instance of BookController.
     * This constructor automatically injects dependencies from BookService and BookMapper.
     * BookService is used to manage business logic related to books,
     * and BookMapper is used to transform between book entities and their DTO representations.
     *
     * @param bookService Service providing operations on books,
     *                   such as retrieval, addition, updating, and deletion.
     */
    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Get a list of all books.
     *
     * @return List of books.
     */
    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(bookMapper::entity2Book)
                .collect(Collectors.toList());
    }

    /**
     * Get a book by its ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return The book with the specified ISBN.
     */
    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable final String isbn) {
        return bookMapper.entity2Book(bookService.getBookByIsbn(isbn));
    }

    /**
     * Save a new book.
     *
     * @param book The book to be saved.
     * @return The saved book.
     */
    @PostMapping()
    public Book saveBook(@RequestBody final Book book) {
        BookEntity bookEntity = bookMapper.book2Entity(book);
        return bookMapper.entity2Book(bookService.saveBook(bookEntity));
    }

    /**
     * Update an existing book by its ISBN.
     *
     * @param isbn The ISBN of the book to be updated.
     * @param book The updated book information.
     * @return The updated book.
     */
    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable final String isbn, @RequestBody final Book book) {
        BookEntity bookEntity = bookMapper.book2Entity(book);
        return bookMapper.entity2Book(bookService.updateBook(isbn, bookEntity));
    }

    /**
     * Delete a book by its ISBN.
     *
     * @param isbn The ISBN of the book to be deleted.
     */
    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable final String isbn) {
        bookService.deleteBook(isbn);
    }
}
