package ru.hpclab.bd.module1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.hpclab.bd.module1.entity.BookEntity;
import ru.hpclab.bd.module1.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    /**
     * Инициализация перед каждым тестом. Открывает моки для всех полей с аннотацией @Mock.
     */
    @BeforeEach
    private void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllBooks_shouldReturnListOfBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(new BookEntity()));
        List<BookEntity> books = bookService.getAllBooks();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        verify(bookRepository).findAll();
    }

    @Test
    public void getBookByIsbn_shouldReturnBookWhenExists() {
        String isbn = "1234567890";
        BookEntity book = new BookEntity();
        when(bookRepository.findById(Long.valueOf(isbn))).thenReturn(Optional.of(book));

        BookEntity foundBook = bookService.getBookByIsbn(isbn);
        assertNotNull(foundBook);
        verify(bookRepository).findById(Long.valueOf(isbn));
    }

    @Test
    public void saveBook_shouldReturnSavedBook() {
        BookEntity book = new BookEntity();
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);

        BookEntity savedBook = bookService.saveBook(book);
        assertNotNull(savedBook);
        verify(bookRepository).save(book);
    }

    @Test
    public void deleteBook_shouldInvokeRepositoryDelete() {
        String isbn = "1234567890";
        when(bookRepository.existsById(Long.valueOf(isbn))).thenReturn(true);
        doNothing().when(bookRepository).deleteById(Long.valueOf(isbn));
        bookService.deleteBook(isbn);
        verify(bookRepository).deleteById(Long.valueOf(isbn));
    }

    @Test
    public void updateBook_shouldReturnUpdatedBook() {
        String isbn = "1234567890";
        BookEntity book = new BookEntity();
        when(bookRepository.findById(Long.valueOf(isbn))).thenReturn(Optional.of(book));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);

        BookEntity updatedBook = bookService.updateBook(isbn, book);
        assertNotNull(updatedBook);
        verify(bookRepository).save(book);
    }

}
