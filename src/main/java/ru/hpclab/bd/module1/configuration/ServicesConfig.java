package ru.hpclab.bd.module1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.bd.module1.model.User;
import ru.hpclab.bd.module1.repository.UserRepository;
import ru.hpclab.bd.module1.service.UserService;

import java.util.UUID;

/**
 * Provides services configuration.
 */
@Configuration
public class ServicesConfig {
    private static final int SUPER_USERS_COUNT = 5;

    /**
     * Builds and inits new user service.
     * @param userRepository user repository
     * @return new user service
     */
    @Bean
    UserService userService(final UserRepository userRepository) {
        UserService userService = new UserService(userRepository);
        for (int i = 0; i < SUPER_USERS_COUNT; i++) {
            userRepository.save(new User(UUID.randomUUID(), "new super user"));
        }
        return userService;
    }
}
