package ru.hpclab.bd.module1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.hpclab.bd.module1.entity.UserEntity;
import ru.hpclab.bd.module1.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    /**
     * Инициализация перед каждым тестом. Открывает моки для всех полей с аннотацией @Mock.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsers_shouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new UserEntity()));
        List<UserEntity> users = userService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    public void getUserById_shouldReturnUserWhenExists() {
        long userId = 1L;
        UserEntity user = new UserEntity();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserEntity foundUser = userService.getUserById(userId);
        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        verify(userRepository).findById(userId);
    }

    @Test
    public void saveUser_shouldReturnSavedUser() {
        UserEntity user = new UserEntity();
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        verify(userRepository).save(user);
    }

    @Test
    public void deleteUser_shouldInvokeRepositoryDelete() {
        long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);
        userService.deleteUser(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    public void updateUser_shouldReturnUpdatedUser() {
        long userId = 1L;
        UserEntity user = new UserEntity();
        user.setId(userId);
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity updatedUser = userService.updateUser(userId, user);
        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.getId());
        verify(userRepository).save(user);
    }

}
