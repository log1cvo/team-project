package com.example.issuemanager.service;

import com.example.issuemanager.exception.ResourceNotFoundException;
import com.example.issuemanager.model.Issue;
import com.example.issuemanager.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    public Issue createIssue(Issue issue) {
        issue.setReportedDate(new Date());
        return issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found"));
    }

    public Issue updateIssue(Long id, Issue issueDetails) {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found"));

        issue.setTitle(issueDetails.getTitle());
        issue.setDescription(issueDetails.getDescription());
        issue.setReporter(issueDetails.getReporter());
        issue.setFixer(issueDetails.getFixer());
        issue.setAssignee(issueDetails.getAssignee());
        issue.setPriority(issueDetails.getPriority());
        issue.setStatus(issueDetails.getStatus());

        return issueRepository.save(issue);
    }

    public void deleteIssue(Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not found"));
        issueRepository.delete(issue);
    }
}
