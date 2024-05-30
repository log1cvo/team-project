package com.example.issuemanagement.service;

import com.example.issuemanagement.exception.ResourceNotFoundException;
import com.example.issuemanagement.model.Issue;
import com.example.issuemanagement.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public Optional<Issue> getIssueById(Long issueId) {
        return issueRepository.findById(issueId);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue updateIssue(Long issueId, Issue issueDetails) {
        Issue issue = getIssueById(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Issue", "id", issueId));
        issue.setTitle(issueDetails.getTitle());
        issue.setDescription(issueDetails.getDescription());
        issue.setReporter(issueDetails.getReporter());
        issue.setAssignee(issueDetails.getAssignee());
        issue.setStatus(issueDetails.getStatus());
        return issueRepository.save(issue);
    }

    public void deleteIssue(Long issueId) {
        Issue issue = getIssueById(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Issue", "id", issueId));
        issueRepository.delete(issue);
    }

    public Page<Issue> findByStatus(String status, Pageable pageable) {
        return issueRepository.findByStatus(status, pageable);
    }

    public Page<Issue> findByReporter(String reporter, Pageable pageable) {
        return issueRepository.findByReporter(reporter, pageable);
    }

    public Page<Issue> findByAssignee(String assignee, Pageable pageable) {
        return issueRepository.findByAssignee(assignee, pageable);
    }
}
