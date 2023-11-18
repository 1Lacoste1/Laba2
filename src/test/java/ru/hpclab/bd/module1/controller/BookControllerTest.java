//package ru.hpclab.bd.module1.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mapstruct.factory.Mappers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.hpclab.bd.module1.Module1Application;
//import ru.hpclab.bd.module1.entity.BookEntity;
//import ru.hpclab.bd.module1.mapper.BookMapper;
//import ru.hpclab.bd.module1.repository.BookRepository;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = Module1Application.class)
//@AutoConfigureMockMvc
//public class BookControllerTest {
//    private MockMvc mvc;
//
//    private BookRepository bookRepository;
//
//    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    private static final int TEST_PAGE_COUNT = 324;
//
//    private static final int TEST_PUBLICATION_YEAR = 2011;
//
//    @Autowired
//    public BookControllerTest(final MockMvc mvc, final BookRepository bookRepository) {
//        this.mvc = mvc;
//        this.bookRepository = bookRepository;
//    }
//
//    @Test
//    public void getBookByIsbn_should_returnBook_when_bookExists() throws Exception {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setIsbn("1234567890");
//        bookEntity.setTitle("Test Book");
//        bookEntity.setPageCount(TEST_PAGE_COUNT);
//        bookEntity.setPublicationYear(TEST_PUBLICATION_YEAR);
//        bookEntity.setAuthorList("Author list");
//
//        BookEntity savedBookEntity = bookRepository.save(bookEntity);
//        String expectedJson = objectMapper.writeValueAsString(bookMapper.entity2Book(savedBookEntity));
//
//        mvc.perform(get("/books/" + bookEntity.getIsbn())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(expectedJson));
//    }
//
//    @Test
//    public void saveBook_should_createBook_and_returnSavedBook() throws Exception {
//        BookEntity newBook = new BookEntity();
//        newBook.setIsbn("1234567890");
//        newBook.setTitle("Test Book");
//        newBook.setPageCount(TEST_PAGE_COUNT);
//        newBook.setPublicationYear(TEST_PUBLICATION_YEAR);
//        newBook.setAuthorList("Author list");
//
//
//        String jsonBook = objectMapper.writeValueAsString(newBook);
//
//        mvc.perform(post("/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonBook))
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonBook));
//    }
//
//    @Test
//    public void updateBook_should_updateBook_and_returnUpdatedBook() throws Exception {
//        // Предварительное создание и сохранение книги
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setIsbn("1234567890");
//        bookEntity.setTitle("Test Book");
//        bookEntity.setPageCount(TEST_PAGE_COUNT);
//        bookEntity.setPublicationYear(TEST_PUBLICATION_YEAR);
//        bookEntity.setAuthorList("Author list");
//        BookEntity savedBookEntity = bookRepository.save(bookEntity);
//
//        // Обновленные данные
//        BookEntity updatedBook = new BookEntity();
//        updatedBook.setTitle("Updated Book");
//        String jsonBook = objectMapper.writeValueAsString(updatedBook);
//
//        mvc.perform(put("/books/" + savedBookEntity.getIsbn())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonBook))
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonBook));
//    }
//
//    @Test
//    public void deleteBook_should_deleteBook_when_bookExists() throws Exception {
//        // Предварительное создание и сохранение книги
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setIsbn("1234567890");
//        bookEntity.setTitle("Test Book");
//        bookEntity.setPageCount(TEST_PAGE_COUNT);
//        bookEntity.setPublicationYear(TEST_PUBLICATION_YEAR);
//        bookEntity.setAuthorList("Author list");
//        BookEntity savedBookEntity = bookRepository.save(bookEntity);
//
//        mvc.perform(delete("/books/" + savedBookEntity.getIsbn()))
//                .andExpect(status().isOk());
//
//        // Опционально: Проверить, что книга действительно удалена
//        assertFalse(bookRepository.existsById(Long.valueOf(savedBookEntity.getIsbn())));
//    }
//
//}
