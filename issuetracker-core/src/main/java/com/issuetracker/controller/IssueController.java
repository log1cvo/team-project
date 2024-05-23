package com.issuetracker.controller;

import com.issuetracker.model.Issue;
import com.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        Issue createdIssue = issueService.createIssue(issue);
        return new ResponseEntity<>(createdIssue, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        List<Issue> issues = issueService.getAllIssues();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        Issue issue = issueService.getIssueById(id);
        if (issue != null) {
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long id, @RequestBody Issue issueDetails) {
        Issue issue = issueService.getIssueById(id);
        if (issue != null) {
            issue.setTitle(issueDetails.getTitle());
            issue.setDescription(issueDetails.getDescription());
            issue.setAssignee(issueDetails.getAssignee());
            issue.setStatus(issueDetails.getStatus());
            issue.setPriority(issueDetails.getPriority());
            issue.setFixer(issueDetails.getFixer());
            Issue updatedIssue = issueService.updateIssue(issue);
            return new ResponseEntity<>(updatedIssue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/assignee/{assignee}")
    public ResponseEntity<List<Issue>> getIssuesByAssignee(@PathVariable String assignee) {
        List<Issue> issues = issueService.getIssuesByAssignee(assignee);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Issue>> getIssuesByStatus(@PathVariable String status) {
        List<Issue> issues = issueService.getIssuesByStatus(status);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/reporter/{reporter}")
    public ResponseEntity<List<Issue>> getIssuesByReporter(@PathVariable String reporter) {
        List<Issue> issues = issueService.getIssuesByReporter(reporter);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
}
