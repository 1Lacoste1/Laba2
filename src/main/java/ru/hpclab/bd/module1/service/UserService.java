package ru.hpclab.bd.module1.service;

import ru.hpclab.bd.module1.model.User;
import ru.hpclab.bd.module1.repository.UserRepository;

import java.util.List;
import java.util.UUID;

/**
 * Service to control users.
 */
public class UserService {

    private final UserRepository userRepository;

    /**
     * Builds new UserService.
     * @param userRepository user repository
     */
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetches all users.
     * @return list of users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Fetches user by id.
     * @param id user id
     * @return user
     */
    public User getUserById(final String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    /**
     * Creates or updates user.
     * @param user user to save
     * @return saved user
     */
    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    /**
     * Updates user.
     * @param id user id to update
     * @param user user with new attributes
     * @return updated user
     */
    public User updateUser(final String id, final User user) {
        user.setIdentifier(UUID.fromString(id));
        return userRepository.put(user);
    }

    /**
     * Deletes user by id.
     * @param id user id
     */
    public void deleteUser(final String id) {
        userRepository.delete(UUID.fromString(id));
    }
}
