package ru.hpclab.bd.module1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hpclab.bd.module1.controller.exeption.UserException;
import ru.hpclab.bd.module1.entity.UserEntity;
import ru.hpclab.bd.module1.repository.UserRepository;

import java.util.List;

import static java.lang.String.format;

/**
 * Service for managing user-related operations.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Сообщение об ошибке, отображаемое, когда пользователь с указанным ID не найден.
     * Это форматированная строка, где '%s' будет заменено на фактический ID пользователя.
     */
    public static final String USER_NOT_FOUND_MSG = "User with ID %s not found";

    /**
     * Конструктор для создания экземпляра UserService.
     *
     * @param userRepository репозиторий для работы с пользователями.
     */
    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get a list of all users.
     *
     * @return List of UserEntity objects.
     */
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The UserEntity corresponding to the provided ID.
     * @throws UserException If the user with the given ID is not found.
     */
    public UserEntity getUserById(final long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException(format(USER_NOT_FOUND_MSG, id)));
    }

    /**
     * Save a new user.
     *
     * @param userEntity The UserEntity object to save.
     * @return The saved UserEntity.
     */
    @Transactional
    public UserEntity saveUser(final UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id The ID of the user to delete.
     */
    @Transactional
    public void deleteUser(final long id) {
        userRepository.deleteById(id);
    }

    /**
     * Update an existing user.
     *
     * @param id         The ID of the user to update.
     * @param userEntity The updated UserEntity object.
     * @return The updated UserEntity.
     */
    @Transactional
    public UserEntity updateUser(final long id, final UserEntity userEntity) {
        userEntity.setId(id);
        return userRepository.save(userEntity);
    }
}
