package com.example.issuemanager;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.repository.IssueRepository;
import com.example.issuemanager.service.IssueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateIssue() {
        Issue issue = new Issue();
        issue.setTitle("Issue Title");
        issue.setDescription("Issue Description");
        issue.setReporter("Reporter");

        when(issueRepository.save(any(Issue.class))).thenReturn(issue);

        Issue createdIssue = issueService.createIssue(issue);

        assertThat(createdIssue.getTitle()).isEqualTo("Issue Title");
        verify(issueRepository, times(1)).save(issue);
    }

    @Test
    public void testGetAllIssues() {
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue());
        issues.add(new Issue());

        when(issueRepository.findAll()).thenReturn(issues);

        List<Issue> foundIssues = issueService.getAllIssues();

        assertThat(foundIssues.size()).isEqualTo(2);
        verify(issueRepository, times(1)).findAll();
    }

    @Test
    public void testGetIssueById() {
        Issue issue = new Issue();
        issue.setId(1L);

        when(issueRepository.findById(1L)).thenReturn(Optional.of(issue));

        Issue foundIssue = issueService.getIssueById(1L);

        assertThat(foundIssue.getId()).isEqualTo(1L);
        verify(issueRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateIssue() {
        Issue existingIssue = new Issue();
        existingIssue.setId(1L);
        existingIssue.setTitle("Existing Issue");

        Issue updatedDetails = new Issue();
        updatedDetails.setTitle("Updated Issue");

        when(issueRepository.findById(1L)).thenReturn(Optional.of(existingIssue));
        when(issueRepository.save(any(Issue.class))).thenReturn(updatedDetails);

        Issue updatedIssue = issueService.updateIssue(1L, updatedDetails);

        assertThat(updatedIssue.getTitle()).isEqualTo("Updated Issue");
        verify(issueRepository, times(1)).findById(1L);
        verify(issueRepository, times(1)).save(existingIssue);
    }

    @Test
    public void testDeleteIssue() {
        Issue issue = new Issue();
        issue.setId(1L);

        when(issueRepository.findById(1L)).thenReturn(Optional.of(issue));

        issueService.deleteIssue(1L);

        verify(issueRepository, times(1)).findById(1L);
        verify(issueRepository, times(1)).delete(issue);
    }
}
