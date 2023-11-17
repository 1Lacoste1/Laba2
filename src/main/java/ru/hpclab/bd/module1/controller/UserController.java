package ru.hpclab.bd.module1.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.bd.module1.model.User;
import ru.hpclab.bd.module1.service.UserService;

import java.util.List;

/**
 * REST controller for managing user-related operations.
 * Provides endpoints for CRUD operations on user entities by delegating to the UserService.
 */
@RestController
@RequestMapping("/users")
@Data
public class UserController {
    private final UserService userService;

    /**
     * Retrieves a list of all users.
     * @return a list of {@link User}
     */
    @GetMapping()
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * Retrieves a single user by their ID.
     * @param id the ID of the user to retrieve
     * @return the {@link User} with the specified ID
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable final String id) {
        return userService.getUserById(id);
    }

    /**
     * Deletes a user by their ID.
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final String id) {
        userService.deleteUser(id);
    }

    /**
     * Creates a new user with the information provided in the request body.
     * @param client the {@link User} to create
     * @return the newly created {@link User}
     */
    @PostMapping()
    public User saveUser(@RequestBody final User client) {
        return userService.saveUser(client);
    }

    /**
     * Updates an existing user identified by the ID, with the information provided in the request body.
     * @param id   the ID of the user to update
     * @param user the {@link User} with updated fields
     * @return the updated {@link User}
     */
    @PutMapping(value = "/{id}")
    public User updateUser(
        @PathVariable(required = false) final String id,
        @RequestBody final User user
    ) {
        return userService.updateUser(id, user);
    }
}
