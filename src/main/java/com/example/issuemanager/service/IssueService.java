package com.example.issuemanager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.repository.IssueRepository;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Optional<Issue> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    public Issue updateIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    public List<Issue> searchIssues(String assignee, String status, String reporter) {
        return issueRepository.findAll().stream()
                .filter(issue -> (assignee == null || issue.getAssignee().equals(assignee)) &&
                                 (status == null || issue.getStatus().equals(status)) &&
                                 (reporter == null || issue.getReporter().equals(reporter)))
                .collect(Collectors.toList());
    }
}
