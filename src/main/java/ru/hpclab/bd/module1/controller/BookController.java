package ru.hpclab.bd.module1.controller;

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
    private final BookMapper bookMapper;

    /**
     * Конструктор для создания экземпляра BookController.
     * Этот конструктор автоматически внедряет зависимости от BookService и BookMapper.
     * BookService используется для управления бизнес-логикой, связанной с книгами,
     * а BookMapper применяется для преобразования между сущностями книг и их представлениями в DTO.
     *
     * @param bookService Сервис, предоставляющий операции с книгами,
     *                   такие как получение, добавление, обновление и удаление.
     * @param bookMapper Маппер, используемый для преобразования между сущностями книг и DTO.
     */
    @Autowired
    public BookController(final BookService bookService, final BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
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
