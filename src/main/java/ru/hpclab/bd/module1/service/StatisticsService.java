package ru.hpclab.bd.module1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import ru.hpclab.bd.module1.service.client.TimeClient;

/**
 * Service for gathering and printing statistics asynchronously.
 */
public class StatisticsService {

    @Value("${statisticsservice.infostring:lines}")
    private String infoString;

    private final int delay;

    private final UserService userService;

    private final BookService bookService;

    private final IssueService issueService;

    private final TimeClient timeClient;

    /**
     * Constructor for StatisticsService with UserService dependency.
     *
     * @param delay      The delay in milliseconds between statistics gathering.
     * @param userService The UserService to gather statistics from.
     * @param timeClient The TimeClient for time-related operations.
     */
    public StatisticsService(final int delay, final UserService userService, final TimeClient timeClient) {
        this.delay = delay;
        this.userService = userService;
        this.timeClient = timeClient;
        this.bookService = null;
        this.issueService = null;
    }

    /**
     * Constructor for StatisticsService with BookService dependency.
     *
     * @param delay      The delay in milliseconds between statistics gathering.
     * @param bookService The BookService to gather statistics from.
     * @param timeClient The TimeClient for time-related operations.
     */
    public StatisticsService(final int delay, final BookService bookService, final TimeClient timeClient) {
        this.delay = delay;
        this.bookService = bookService;
        this.timeClient = timeClient;
        this.userService = null;
        this.issueService = null;
    }

    /**
     * Constructor for StatisticsService with IssueService dependency.
     *
     * @param delay      The delay in milliseconds between statistics gathering.
     * @param issueService The IssueService to gather statistics from.
     * @param timeClient The TimeClient for time-related operations.
     */
    public StatisticsService(final int delay, final IssueService issueService, final TimeClient timeClient) {
        this.delay = delay;
        this.issueService = issueService;
        this.timeClient = timeClient;
        this.userService = null;
        this.bookService = null;
    }

    /**
     * Asynchronously gather and print statistics at a fixed rate.
     *
     * @throws InterruptedException If the thread is interrupted during sleep.
     */
    @Async(value = "applicationTaskExecutor")
    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
                timeClient.getLocal() + " - Fixed rate task async - " + delay + " - " + infoString + " - "
                        + (userService != null ? userService.getAllUsers().size() : 0));
        System.out.println(
                timeClient.getLocal() + " - Fixed rate task async - " + delay + " - " + infoString + " - "
                        + (bookService != null ? bookService.getAllBooks().size() : 0));

        System.out.println(
                timeClient.getLocal() + " - Fixed rate task async - " + delay + " - " + infoString + " - "
                        + (issueService != null ? issueService.getAllIssues().size() : 0));

        Thread.sleep(delay);
    }
}
