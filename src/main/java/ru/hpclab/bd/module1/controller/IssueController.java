package ru.hpclab.bd.module1.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.bd.module1.model.Issue;
import ru.hpclab.bd.module1.service.IssueService;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing issues related to book borrowing.
 * This class handles HTTP requests related to issues, such as creating, retrieving, and deleting issues.
 */
@RestController
@RequestMapping("/issues")
@Data
public class IssueController {
    private final IssueService issueService;

    /**
     * Handles HTTP POST request to create a new issue.
     * @param issue the Issue object representing the book issue (should be final)
     * @return a ResponseEntity with the created Issue object if successful
     */
    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody final Issue issue) {
        return ResponseEntity.ok(issueService.issueBookToUser(issue));
    }

    /**
     * Handles HTTP GET request to retrieve all issues.
     * @return a ResponseEntity with a list of all Issue objects
     */
    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    /**
     * Handles HTTP GET request to retrieve an issue by its ID.
     * @param id the unique identifier of the Issue to retrieve (should be final)
     * @return a ResponseEntity with the retrieved Issue object if found, or a not found response if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable final UUID id) {
        Issue issue = issueService.getIssueById(id);
        if (issue != null) {
            return ResponseEntity.ok(issue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Handles HTTP DELETE request to delete an issue by its ID.
     * @param id the unique identifier of the Issue to delete (should be final)
     * @return a ResponseEntity with a success response if deleted, or a not found response if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable final UUID id) {
        boolean deleted = issueService.deleteIssueById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
