package ru.hpclab.bd.module1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.bd.module1.model.Book;
import ru.hpclab.bd.module1.model.Issue;
import ru.hpclab.bd.module1.model.User;
import ru.hpclab.bd.module1.repository.BookRepository;
import ru.hpclab.bd.module1.repository.UserRepository;
import ru.hpclab.bd.module1.service.BookService;
import ru.hpclab.bd.module1.service.IssueService;
import ru.hpclab.bd.module1.service.UserService;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

/**
 * Configuration class that defines beans related to user and book services.
 * It sets up the application context with predefined beans for the UserService and BookService.
 */
@Configuration
public class ServicesConfig {
    private static final int SUPER_USERS_COUNT = 5;

    private static final int SUPER_BOOK_COUNT = 25;

    private static final int SUPER_ISSUES_COUNT = 5;

    /**
     * Bean creation method for UserService. It pre-populates the UserRepository with SUPER_USERS_COUNT
     * number of 'super users'.
     * @param userRepository the repository used by the UserService for data access
     * @return the initialized UserService with pre-populated users
     */
    @Bean
    UserService userService(final UserRepository userRepository) {
        Random random = new Random();
        UserService userService = new UserService(userRepository);
        for (int i = 0; i < SUPER_USERS_COUNT; i++) {
            userRepository.save(new User(UUID.randomUUID(), "new super user", "new super year of birthday" , random.nextInt(1000)));
        }
        return userService;
    }

    /**
     * Bean creation method for BookService. It pre-populates the BookRepository with SUPER_BOOK_COUNT
     * number of 'super books'.
     * @param bookRepository the repository used by the BookService for data access
     * @return the initialized BookService with pre-populated books
     */
    @Bean
    BookService bookService(final BookRepository bookRepository) {
        Random random = new Random();
        BookService bookService = new BookService(bookRepository);
        for (int i = 0; i < SUPER_BOOK_COUNT; i++) {
            bookRepository.save(new Book(UUID.randomUUID(), random.nextInt(1000000000), "new super book", "new super author",
                    random.nextInt(2023), random.nextInt(600)));
        }
        return bookService;
    }

    /**
     * Bean creation method for IssueService. It pre-populates the IssueService with SUPER_ISSUES_COUNT
     * number of 'super issues'.
     * @return the initialized IssueService with pre-populated issues
     */
    @Bean
    public IssueService issueService() {
        Random random = new Random();
        IssueService issueService = new IssueService();

        for (int i = 0; i < SUPER_ISSUES_COUNT; i++) {
            int year = random.nextInt(50) + 1970;
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(28) + 1;

            User user = new User(UUID.randomUUID(), "new super user", "new super year of birthday" , random.nextInt(1000));
            Book book = new Book(UUID.randomUUID(), random.nextInt(1000000000), "new super book", "new super author",
                    random.nextInt(2023), random.nextInt(600));

            issueService.save(new Issue(UUID.randomUUID(), user, book, LocalDate.of(year, month, day), 4));
        }
        return issueService;
    }
}
