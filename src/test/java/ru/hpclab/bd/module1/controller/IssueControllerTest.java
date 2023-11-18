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
//import ru.hpclab.bd.module1.controller.exeption.BookException;
//import ru.hpclab.bd.module1.controller.exeption.UserException;
//import ru.hpclab.bd.module1.entity.BookEntity;
//import ru.hpclab.bd.module1.entity.IssueEntity;
//import ru.hpclab.bd.module1.entity.UserEntity;
//import ru.hpclab.bd.module1.mapper.IssueMapper;
//import ru.hpclab.bd.module1.repository.BookRepository;
//import ru.hpclab.bd.module1.repository.IssueRepository;
//import ru.hpclab.bd.module1.repository.UserRepository;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = Module1Application.class)
//@AutoConfigureMockMvc
//public class IssueControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private IssueRepository issueRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    private final IssueMapper issueMapper = Mappers.getMapper(IssueMapper.class);
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    private static final int USAGE_PERIOD = 30;
//
//    private static final long USER_ID = 3;
//
//    @Test
//    public void getIssueById_should_returnIssue_when_issueExists() throws Exception {
//        IssueEntity issueEntity = new IssueEntity();
//        UserEntity user = userRepository.findById((long) (1))
//                .orElseThrow(() -> new UserException("User not found"));
//        BookEntity book = bookRepository.findById(Long.valueOf("isbn"))
//                .orElseThrow(() -> new BookException("Book not found"));
//
//        issueEntity.setUser(user);
//        issueEntity.setBook(book);
//        issueEntity.setIssueDate(LocalDate.now());
//        issueEntity.setUsagePeriod(USAGE_PERIOD);
//        IssueEntity savedIssueEntity = issueRepository.save(issueEntity);
//        String expectedJson = objectMapper.writeValueAsString(issueMapper.entity2Issue(savedIssueEntity));
//
//        mvc.perform(get("/issues/" + issueEntity.getId())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(expectedJson));
//    }
//
//    @Test
//    public void updateIssue_should_updateIssue_and_returnUpdatedIssue() throws Exception {
//        IssueEntity issueEntity = new IssueEntity();
//        UserEntity user = userRepository.findById((long) (1))
//                .orElseThrow(() -> new UserException("User not found"));
//        BookEntity book = bookRepository.findById(Long.valueOf("isbn"))
//                .orElseThrow(() -> new BookException("Book not found"));
//
//        issueEntity.setUser(user);
//        issueEntity.setBook(book);
//        issueEntity.setIssueDate(LocalDate.now());
//        issueEntity.setUsagePeriod(USAGE_PERIOD);
//        IssueEntity savedIssueEntity = issueRepository.save(issueEntity);
//
//        IssueEntity updatedIssue = new IssueEntity();
//        UserEntity user2 = userRepository.findById((long) (USER_ID))
//                .orElseThrow(() -> new UserException("User not found"));
//        BookEntity book2 = bookRepository.findById(Long.valueOf("isbn1"))
//                .orElseThrow(() -> new BookException("Book not found"));
//
//        updatedIssue.setUser(user2);
//        updatedIssue.setBook(book2);
//        updatedIssue.setIssueDate(LocalDate.now());
//        updatedIssue.setUsagePeriod(USAGE_PERIOD);
//
//        String jsonIssue = objectMapper.writeValueAsString(updatedIssue);
//
//        mvc.perform(put("/issues/" + savedIssueEntity.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonIssue))
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonIssue));
//    }
//
//    @Test
//    public void deleteIssue_should_deleteIssue_when_issueExists() throws Exception {
//        IssueEntity issueEntity = new IssueEntity();
//        UserEntity user = userRepository.findById((long) (1))
//                .orElseThrow(() -> new UserException("User not found"));
//        BookEntity book = bookRepository.findById(Long.valueOf("isbn"))
//                .orElseThrow(() -> new BookException("Book not found"));
//
//        issueEntity.setUser(user);
//        issueEntity.setBook(book);
//        issueEntity.setIssueDate(LocalDate.now());
//        issueEntity.setUsagePeriod(USAGE_PERIOD);
//        IssueEntity savedIssueEntity = issueRepository.save(issueEntity);
//
//        mvc.perform(delete("/issues/" + savedIssueEntity.getId()))
//                .andExpect(status().isOk());
//
//        assertFalse(issueRepository.existsById(savedIssueEntity.getId()));
//    }
//
//}
