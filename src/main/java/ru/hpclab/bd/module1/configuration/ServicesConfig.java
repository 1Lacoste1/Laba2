package ru.hpclab.bd.module1.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.bd.module1.controller.exeption.BookException;
import ru.hpclab.bd.module1.controller.exeption.UserException;
import ru.hpclab.bd.module1.entity.BookEntity;
import ru.hpclab.bd.module1.entity.IssueEntity;
import ru.hpclab.bd.module1.entity.UserEntity;
import ru.hpclab.bd.module1.repository.BookRepository;
import ru.hpclab.bd.module1.repository.IssueRepository;
import ru.hpclab.bd.module1.repository.UserRepository;
import ru.hpclab.bd.module1.service.BookService;
import ru.hpclab.bd.module1.service.IssueService;
import ru.hpclab.bd.module1.service.StatisticsService;
import ru.hpclab.bd.module1.service.UserService;
import ru.hpclab.bd.module1.service.client.TimeClient;

import java.time.LocalDate;

/**
 * Configuration class that defines beans related to user and book services.
 * It sets up the application context with predefined beans for UserService and BookService.
 */
@Configuration
public class ServicesConfig {
    private static final int NUM_USERS = 5;
    private static final int BIRTH_YEAR = 1990;
    private static final int START_YEAR = 2000;
    private static final int START_MONTH = 1;
    private static final int START_DAY = 1;
    private static final int PAGE_COUNT_INCREMENT = 10;
    private static final int USAGE_PERIOD = 30;
    private static final int DELAY_COUNT1 = 1000;
    private static final int DELAY_COUNT2 = 2000;

    /**
     * Creates a UserService bean and populates it with sample data.
     *
     * @param userRepository The user repository.
     * @return UserService bean with populated data.
     */
    @Bean
    UserService userService(final UserRepository userRepository) {
        UserService userService = new UserService(userRepository);

        for (int i = 0; i < NUM_USERS; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setFullName("User");
            userEntity.setBirthDate(LocalDate.of(BIRTH_YEAR, START_MONTH, START_DAY));
            userEntity.setLibraryCardNumber("LCN");
            userRepository.save(userEntity);
        }

        return userService;
    }

    /**
     * Creates a BookService bean and populates it with sample data.
     *
     * @param bookRepository The book repository.
     * @return BookService bean with populated data.
     */
    @Bean
    BookService bookService(final BookRepository bookRepository) {
        BookService bookService = new BookService(bookRepository);

        // Sample data creation for demonstration purposes
        for (int i = 0; i < NUM_USERS; i++) {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setIsbn("isbn");
            bookEntity.setTitle("Title");
            bookEntity.setAuthorList("Author");
            bookEntity.setPublicationYear(START_YEAR);
            bookEntity.setPageCount(PAGE_COUNT_INCREMENT);

            bookRepository.save(bookEntity);
        }
        return bookService;
    }

    /**
     * Creates an IssueService bean and populates it with sample data.
     *
     * @param issueRepository The issue repository.
     * @param userRepository  The user repository.
     * @param bookRepository  The book repository.
     * @return IssueService bean with populated data.
     */
    @Bean
    IssueService issueService(final IssueRepository issueRepository,
                              final UserRepository userRepository,
                              final BookRepository bookRepository) {
        IssueService issueService = new IssueService(issueRepository);

        // Sample data creation for demonstration purposes
        for (int i = 0; i < NUM_USERS; i++) {
            IssueEntity issueEntity = new IssueEntity();
            UserEntity user = userRepository.findById((long) (i + 1))
                    .orElseThrow(() -> new UserException("User not found"));
            BookEntity book = bookRepository.findById(Long.valueOf("isbn"))
                    .orElseThrow(() -> new BookException("Book not found"));

            issueEntity.setUser(user);
            issueEntity.setBook(book);
            issueEntity.setIssueDate(LocalDate.now());
            issueEntity.setUsagePeriod(USAGE_PERIOD);

            issueRepository.save(issueEntity);
        }
        return issueService;
    }

    /**
     * Creates a StatisticsService bean for console output with a 2000-millisecond period.
     *
     * @param userService  The UserService bean.
     * @param timeClient   The time client.
     * @return StatisticsService bean with a 2000-millisecond period.
     */
    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console2000")
    StatisticsService statisticsService2000(final UserService userService, final TimeClient timeClient) {
        return new StatisticsService(DELAY_COUNT2, userService, timeClient);
    }

    /**
     * Creates a StatisticsService bean for console output with a 1000-millisecond period.
     *
     * @param userService  The UserService bean.
     * @param timeClient   The time client.
     * @return StatisticsService bean with a 1000-millisecond period.
     */
    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console1000")
    StatisticsService statisticsService1000(final UserService userService, final TimeClient timeClient) {
        return new StatisticsService(DELAY_COUNT1, userService, timeClient);
    }

    /**
     * Creates a StatisticsService bean for console output with a 1000-millisecond period.
     *
     * @param bookService  The BookService bean.
     * @param timeClient   The time client.
     * @return StatisticsService bean with a 1000-millisecond period.
     */
    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console1000")
    StatisticsService statisticsService1000(final BookService bookService, final TimeClient timeClient) {
        return new StatisticsService(DELAY_COUNT1, bookService, timeClient);
    }

    /**
     * Creates a StatisticsService bean for console output with a 2000-millisecond period.
     *
     * @param bookService  The BookService bean.
     * @param timeClient   The time client.
     * @return StatisticsService bean with a 2000-millisecond period.
     */
    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console2000")
    StatisticsService statisticsService2000(final BookService bookService, final TimeClient timeClient) {
        return new StatisticsService(DELAY_COUNT2, bookService, timeClient);
    }

    /**
     * Creates a StatisticsService bean for console output with a 1000-millisecond period.
     *
     * @param issueService The IssueService bean.
     * @param timeClient   The time client.
     * @return StatisticsService bean with a 1000-millisecond period.
     */
    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console1000")
    StatisticsService statisticsService1000(final IssueService issueService, final TimeClient timeClient) {
        return new StatisticsService(DELAY_COUNT1, issueService, timeClient);
    }

    /**
     * Creates a StatisticsService bean for console output with a 2000-millisecond period.
     *
     * @param issueService The IssueService bean.
     * @param timeClient   The time client.
     * @return StatisticsService bean with a 2000-millisecond period.
     */
    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console2000")
    StatisticsService statisticsService2000(final IssueService issueService, final TimeClient timeClient) {
        return new StatisticsService(DELAY_COUNT2, issueService, timeClient);
    }

}
