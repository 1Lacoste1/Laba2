package ru.hpclab.bd.module1.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hpclab.bd.module1.controller.exeption.IssueException;
import ru.hpclab.bd.module1.entity.IssueEntity;
import ru.hpclab.bd.module1.repository.IssueRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing issues.
 */
@Service
@Data
@Transactional(readOnly = true)
public class IssueService {
    private final IssueRepository issueRepository;

    /**
     * Error message used when an issue with the specified ID already exists in the system.
     * This is a formatted string where '%s' will be replaced with the actual issue ID.
     */
    public static final String ISSUE_EXISTS_MSG = "Issue with ID %s already exists";

    /**
     * Constructor for creating an instance of IssueService.
     *
     * @param issueRepository Repository for working with issues,
     *                        providing access to the database.
     */
    @Autowired
    public IssueService(final IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    /**
     * Get a list of all issues.
     *
     * @return List of IssueEntity representing all issues.
     */
    public List<IssueEntity> getAllIssues() {
        return issueRepository.findAll();
    }

    /**
     * Get an issue by its ID.
     *
     * @param id The ID of the issue to retrieve.
     * @return The IssueEntity representing the issue, or empty if not found.
     */
    public Optional<IssueEntity> getIssueById(final Long id) {
        return issueRepository.findById(id);
    }

    /**
     * Add a new issue.
     *
     * @param issue The IssueEntity to add.
     * @return The added IssueEntity.
     */
    @Transactional
    public IssueEntity addIssue(final IssueEntity issue) {
        return issueRepository.save(issue);
    }

    /**
     * Update an existing issue.
     *
     * @param id           The ID of the issue to update.
     * @param issueDetails The updated IssueEntity.
     * @return The updated IssueEntity.
     * @throws IssueException If the issue does not exist.
     */
    @Transactional
    public IssueEntity updateIssue(final Long id, final IssueEntity issueDetails) {
        final IssueEntity issue = issueRepository.findById(id)
                .orElseThrow(() -> new IssueException(String.format(ISSUE_EXISTS_MSG, id)));
        issue.setUser(issueDetails.getUser());
        issue.setBook(issueDetails.getBook());
        issue.setIssueDate(issueDetails.getIssueDate());
        issue.setUsagePeriod(issueDetails.getUsagePeriod());
        return issueRepository.save(issue);
    }

    /**
     * Delete an issue by its ID.
     *
     * @param id The ID of the issue to delete.
     * @throws IssueException If the issue does not exist.
     */
    @Transactional
    public void deleteIssue(final Long id) {
        final IssueEntity issue = issueRepository.findById(id)
                .orElseThrow(() -> new IssueException(String.format(ISSUE_EXISTS_MSG, id)));
        issueRepository.delete(issue);
    }

    /**
     * Get a list of issues by user ID.
     *
     * @param userId The ID of the user to retrieve issues for.
     * @return List of IssueEntity representing the user's issues.
     */
    public List<IssueEntity> getIssuesByUser(final Long userId) {
        return issueRepository.findByUserId(userId);
    }

    /**
     * Get a list of issues by book ISBN.
     *
     * @param isbn The ISBN of the book to retrieve issues for.
     * @return List of IssueEntity representing the book's issues.
     */
    public List<IssueEntity> getIssuesByBook(final String isbn) {
        return issueRepository.findByBookIsbn(isbn);
    }
}
