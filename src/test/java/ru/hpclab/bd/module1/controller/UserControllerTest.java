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
//import ru.hpclab.bd.module1.entity.UserEntity;
//import ru.hpclab.bd.module1.mapper.UserMapper;
//import ru.hpclab.bd.module1.repository.UserRepository;
//
//import java.time.LocalDate;
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
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    private static final int START_YEAR = 2000;
//
//    private static final int START_MONTH = 1;
//
//    private static final int START_DAY = 1;
//
//
//    @Test
//    public void getUserById_should_returnUser_when_userExists() throws Exception {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setFullName("User");
//        userEntity.setBirthDate(LocalDate.of(START_YEAR, START_MONTH, START_DAY));
//        userEntity.setLibraryCardNumber("LCN");
//        UserEntity savedUserEntity = userRepository.save(userEntity);
//        String expectedJson = objectMapper.writeValueAsString(userMapper.entity2User(savedUserEntity));
//
//        mvc.perform(get("/users/" + userEntity.getId())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(expectedJson));
//    }
//
//    @Test
//    public void deleteUser_should_deleteUser_when_userExists() throws Exception {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setFullName("User");
//        userEntity.setBirthDate(LocalDate.of(START_YEAR, START_MONTH, START_DAY));
//        userEntity.setLibraryCardNumber("LCN");
//        UserEntity savedUserEntity = userRepository.save(userEntity);
//
//        mvc.perform(delete("/users/" + savedUserEntity.getId()))
//                .andExpect(status().isOk());
//
//        assertFalse(userRepository.existsById(savedUserEntity.getId()));
//    }
//
//    @Test
//    public void saveUser_should_createUser_and_returnSavedUser() throws Exception {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setFullName("User");
//        userEntity.setBirthDate(LocalDate.of(START_YEAR, START_MONTH, START_DAY));
//        userEntity.setLibraryCardNumber("LCN");
//        String jsonUser = objectMapper.writeValueAsString(userEntity);
//
//        mvc.perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonUser))
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonUser));
//    }
//
//    @Test
//    public void updateUser_should_updateUser_and_returnUpdatedUser() throws Exception {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setFullName("User");
//        userEntity.setBirthDate(LocalDate.of(START_YEAR, START_MONTH, START_DAY));
//        userEntity.setLibraryCardNumber("LCN");
//        UserEntity savedUserEntity = userRepository.save(userEntity);
//
//        UserEntity updatedUser = new UserEntity();
//        updatedUser.setFullName("User1");
//        updatedUser.setBirthDate(LocalDate.of(START_YEAR, START_MONTH, START_DAY));
//        updatedUser.setLibraryCardNumber("LCN1");
//
//        String jsonUser = objectMapper.writeValueAsString(updatedUser);
//
//        mvc.perform(put("/users/" + savedUserEntity.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonUser))
//                .andExpect(status().isOk())
//                .andExpect(content().json(jsonUser));
//    }
//}
