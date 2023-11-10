package ru.hpclab.bd.module1.service;

import lombok.Data;
import org.springframework.util.ObjectUtils;
import ru.hpclab.bd.module1.controller.exeption.IssueException;
import ru.hpclab.bd.module1.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

/**
 * Service class for managing issues related to book borrowing.
 * This class provides methods for creating, retrieving, and deleting issues.
 */
@Data
public class IssueService {
    private final List<Issue> issues = new ArrayList<>();

    public static final String ISSUE_EXISTS_MSG = "Issue with ID %s already exists";

    /**
     * Issues a book to a user.
     * @param issue the Issue object representing the book issue
     * @return the issued Issue object
     */
    public Issue issueBookToUser(Issue issue) {
        issue.setId(UUID.randomUUID());
        issues.add(issue);
        return issue;
    }

    /**
     * Retrieves all issues.
     * @return a list of all Issue objects
     */
    public List<Issue> getAllIssues() {
        return new ArrayList<>(issues);
    }

    /**
     * Retrieves an issue by its ID.
     * @param id the unique identifier of the Issue to retrieve
     * @return the retrieved Issue object if found, or null if not found
     */
    public Issue getIssueById(UUID id) {
        return issues.stream()
                .filter(issue -> issue.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Saves an issue. If the issue's identifier is not set, it will generate a new UUID.
     * @param issue the Issue object to save
     * @return the saved Issue object with an identifier
     * @throws IssueException if the issue already exists
     */
    public Issue save(final Issue issue) {
        if (ObjectUtils.isEmpty(issue.getId())) {
            issue.setId(UUID.randomUUID());
        }

        for (int i = 0; i < issues.size(); i++) {
            Issue issueData = issues.get(i);
            if (issueData.getId().equals(issue.getId())) {
                throw new IssueException(format(ISSUE_EXISTS_MSG, issue.getId()));
            }
        }

        issues.add(issue);

        return issue;
    }

    /**
     * Deletes an issue by its ID.
     * @param id the unique identifier of the Issue to delete
     * @return true if the issue was successfully deleted, false if not found
     */
    public boolean deleteIssueById(UUID id) {
        return issues.removeIf(issue -> issue.getId().equals(id));
    }
}
