package ru.hpclab.bd.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.bd.module1.entity.IssueEntity;
import ru.hpclab.bd.module1.mapper.IssueMapper;
import ru.hpclab.bd.module1.model.Issue;
import ru.hpclab.bd.module1.service.IssueService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for handling issue-related HTTP requests.
 */
@RestController
@RequestMapping("/issues")
public class IssueController {
    private final IssueService issueService;
    private final IssueMapper issueMapper;

    /**
     * Конструктор для создания экземпляра IssueController.
     * Этот конструктор использует автоматическое внедрение зависимостей IssueService и IssueMapper,
     * необходимых для управления данными о проблемах (issues) и их преобразования.
     *
     * @param issueService Сервис, предоставляющий бизнес-логику для операций с проблемами.
     * @param issueMapper  Маппер, используемый для преобразования между сущностями и DTO проблем.
     */
    @Autowired
    public IssueController(final IssueService issueService, final IssueMapper issueMapper) {
        this.issueService = issueService;
        this.issueMapper = issueMapper;
    }

    /**
     * Get a list of all issues.
     *
     * @return List of issues.
     */
    @GetMapping()
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues().stream()
                .map(issueMapper::entity2Issue).collect(Collectors.toList());
    }

    /**
     * Get an issue by its ID.
     *
     * @param id The ID of the issue.
     * @return The issue with the specified ID.
     */
    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable final long id) {
        return issueMapper.entity2Issue(issueService.getIssueById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"))); // Custom exception can be used here
    }

    /**
     * Get a list of issues by user ID.
     *
     * @param userId The ID of the user.
     * @return List of issues associated with the user.
     */
    @GetMapping("/user/{userId}")
    public List<Issue> getIssuesByUserId(@PathVariable final long userId) {
        return issueService.getIssuesByUser(userId).stream()
                .map(issueMapper::entity2Issue).collect(Collectors.toList());
    }

    /**
     * Get a list of issues by book ISBN.
     *
     * @param isbn The ISBN of the book.
     * @return List of issues associated with the book.
     */
    @GetMapping("/book/{isbn}")
    public List<Issue> getIssuesByBookIsbn(@PathVariable final String isbn) {
        return issueService.getIssuesByBook(isbn).stream()
                .map(issueMapper::entity2Issue).collect(Collectors.toList());
    }

    /**
     * Add a new issue.
     *
     * @param issue The issue to be added.
     * @return The added issue.
     */
    @PostMapping()
    public Issue addIssue(@RequestBody final Issue issue) {
        IssueEntity issueEntity = issueMapper.issue2Entity(issue);
        return issueMapper.entity2Issue(issueService.addIssue(issueEntity));
    }

    /**
     * Update an existing issue by its ID.
     *
     * @param id    The ID of the issue to be updated.
     * @param issue The updated issue information.
     * @return The updated issue.
     */
    @PutMapping("/{id}")
    public Issue updateIssue(@PathVariable final long id, @RequestBody final Issue issue) {
        IssueEntity issueEntity = issueMapper.issue2Entity(issue);
        return issueMapper.entity2Issue(issueService.updateIssue(id, issueEntity));
    }

    /**
     * Delete an issue by its ID.
     *
     * @param id The ID of the issue to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable final long id) {
        issueService.deleteIssue(id);
    }
}
