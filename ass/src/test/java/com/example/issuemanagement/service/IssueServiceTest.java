package com.example.issuemanagement.service;

import com.example.issuemanagement.model.Issue;
import com.example.issuemanagement.repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    @Test
    void testGetAllIssues() {
        Issue issue1 = new Issue(1L, "Issue 1", "Description 1", "Reporter 1", "Assignee 1", "open");
        Issue issue2 = new Issue(2L, "Issue 2", "Description 2", "Reporter 2", "Assignee 2", "closed");
        List<Issue> issues = Arrays.asList(issue1, issue2);

        when(issueRepository.findAll()).thenReturn(issues);

        List<Issue> result = issueService.getAllIssues();
        assertEquals(2, result.size());
        verify(issueRepository, times(1)).findAll();
    }

    @Test
    void testGetIssueById() {
        Issue issue = new Issue(1L, "Issue 1", "Description 1", "Reporter 1", "Assignee 1", "open");

        when(issueRepository.findById(1L)).thenReturn(Optional.of(issue));

        Optional<Issue> result = issueService.getIssueById(1L);
        assertEquals("Issue 1", result.get().getTitle());
        verify(issueRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateIssue() {
        Issue issue = new Issue(null, "Issue 1", "Description 1", "Reporter 1", "Assignee 1", "open");

        when(issueRepository.save(issue)).thenReturn(issue);

        Issue result = issueService.createIssue(issue);
        assertEquals("Issue 1", result.getTitle());
        verify(issueRepository, times(1)).save(issue);
    }

    @Test
    void testUpdateIssue() {
        Issue existingIssue = new Issue(1L, "Issue 1", "Description 1", "Reporter 1", "Assignee 1", "open");
        Issue updatedIssue = new Issue(1L, "Updated Issue", "Updated Description", "Reporter 1", "Assignee 1", "closed");

        when(issueRepository.findById(1L)).thenReturn(Optional.of(existingIssue));
        when(issueRepository.save(existingIssue)).thenReturn(updatedIssue);

        Issue result = issueService.updateIssue(1L, updatedIssue);
        assertEquals("Updated Issue", result.getTitle());
        verify(issueRepository, times(1)).findById(1L);
        verify(issueRepository, times(1)).save(existingIssue);
    }

    @Test
    void testDeleteIssue() {
        Issue issue = new Issue(1L, "Issue 1", "Description 1", "Reporter 1", "Assignee 1", "open");

        when(issueRepository.findById(1L)).thenReturn(Optional.of(issue));
        doNothing().when(issueRepository).delete(issue);

        issueService.deleteIssue(1L);
        verify(issueRepository, times(1)).findById(1L);
        verify(issueRepository, times(1)).delete(issue);
    }
}
