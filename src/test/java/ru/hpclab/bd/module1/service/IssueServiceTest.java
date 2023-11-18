package ru.hpclab.bd.module1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.hpclab.bd.module1.entity.IssueEntity;
import ru.hpclab.bd.module1.repository.IssueRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    /**
     * Инициализация перед каждым тестом. Открывает моки для всех полей с аннотацией @Mock.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllIssues_shouldReturnListOfIssues() {
        when(issueRepository.findAll()).thenReturn(List.of(new IssueEntity()));
        List<IssueEntity> issues = issueService.getAllIssues();
        assertNotNull(issues);
        assertFalse(issues.isEmpty());
        verify(issueRepository).findAll();
    }

    @Test
    public void getIssueById_shouldReturnIssueWhenExists() {
        Long issueId = 1L;
        IssueEntity issue = new IssueEntity();
        when(issueRepository.findById(issueId)).thenReturn(Optional.of(issue));

        Optional<IssueEntity> foundIssue = issueService.getIssueById(issueId);
        assertTrue(foundIssue.isPresent());
        assertEquals(issue, foundIssue.get());
        verify(issueRepository).findById(issueId);
    }

    @Test
    public void addIssue_shouldReturnAddedIssue() {
        IssueEntity issue = new IssueEntity();
        when(issueRepository.save(any(IssueEntity.class))).thenReturn(issue);

        IssueEntity addedIssue = issueService.addIssue(issue);
        assertNotNull(addedIssue);
        verify(issueRepository).save(issue);
    }

    @Test
    public void updateIssue_shouldReturnUpdatedIssue() {
        Long issueId = 1L;
        IssueEntity existingIssue = new IssueEntity();
        when(issueRepository.findById(issueId)).thenReturn(Optional.of(existingIssue));
        when(issueRepository.save(any(IssueEntity.class))).thenReturn(existingIssue);

        IssueEntity updatedIssue = issueService.updateIssue(issueId, new IssueEntity());
        assertNotNull(updatedIssue);
        verify(issueRepository).save(existingIssue);
    }

    @Test
    public void deleteIssue_shouldInvokeRepositoryDelete() {
        Long issueId = 1L;
        IssueEntity existingIssue = new IssueEntity();
        when(issueRepository.findById(issueId)).thenReturn(Optional.of(existingIssue));
        doNothing().when(issueRepository).delete(any(IssueEntity.class));

        issueService.deleteIssue(issueId);
        verify(issueRepository).delete(existingIssue);
    }

}
