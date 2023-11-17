package ru.hpclab.bd.module1.service;

import lombok.Data;
import ru.hpclab.bd.module1.model.User;
import ru.hpclab.bd.module1.repository.UserRepository;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing users.
 * It handles business operations for user entities by using UserRepository for data access.
 */
@Data
public class UserService {
    private final UserRepository userRepository;

    /**
     * Retrieves all users from the repository.
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their identifier.
     * @param id the identifier of the user to retrieve
     * @return the user with the specified identifier
     */
    public User getUserById(final String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    /**
     * Saves a user entity to the repository.
     * @param user the user to save
     * @return the saved user entity
     */
    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    /**
     * Updates a user entity with a given identifier.
     * @param id   the identifier of the user to update
     * @param user the user entity with updated information
     * @return the updated user entity
     */
    public User updateUser(final String id, final User user) {
        user.setIdentifier(UUID.fromString(id));
        return userRepository.put(user);
    }

    /**
     * Deletes a user entity from the repository by their identifier.
     * @param id the identifier of the user to delete
     */
    public void deleteUser(final String id) {
        userRepository.delete(UUID.fromString(id));
    }
}
