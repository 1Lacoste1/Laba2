package ru.hpclab.bd.module1.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.bd.module1.mapper.UserMapper;
import ru.hpclab.bd.module1.model.User;
import ru.hpclab.bd.module1.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for handling user-related HTTP requests.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    /**
     * Creates an instance of UserController to handle requests related to users.
     * This constructor automatically injects dependencies from UserService and UserMapper,
     * which are used to manage user data and their transformation.
     *
     * @param userService Service providing logic for managing users.
     */
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Get a list of all users.
     *
     * @return List of users.
     */
    @GetMapping()
    public List<User> getUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::entity2User).collect(Collectors.toList());
    }

    /**
     * Get a user by their ID.
     *
     * @param id The ID of the user.
     * @return The user with the specified ID.
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable final long id) {
        return userMapper.entity2User(userService.getUserById(id));
    }

    /**
     * Delete a user by their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final long id) {
        userService.deleteUser(id);
    }

    /**
     * Save a new user.
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    @PostMapping()
    public User saveUser(@RequestBody final User user) {
        return userMapper.entity2User(userService.saveUser(userMapper.user2Entity(user)));
    }

    /**
     * Update an existing user by their ID.
     *
     * @param id   The ID of the user to be updated.
     * @param user The updated user information.
     * @return The updated user.
     */
    @PutMapping(value = "/{id}")
    public User updateUser(@PathVariable(required = false) final long id, @RequestBody final User user) {
        return userMapper.entity2User(userService.updateUser(id, userMapper.user2Entity(user)));
    }
}
