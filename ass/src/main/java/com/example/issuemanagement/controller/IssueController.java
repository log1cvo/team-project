package com.example.issuemanagement.controller;

import com.example.issuemanagement.exception.ResourceNotFoundException;
import com.example.issuemanagement.model.Issue;
import com.example.issuemanagement.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable(value = "id") Long issueId) throws ResourceNotFoundException {
        Issue issue = issueService.getIssueById(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Issue", "id", issueId));
        return ResponseEntity.ok().body(issue);
    }

    @PostMapping
    public Issue createIssue(@Valid @RequestBody Issue issue) {
        return issueService.createIssue(issue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable(value = "id") Long issueId,
                                             @Valid @RequestBody Issue issueDetails) throws ResourceNotFoundException {
        Issue updatedIssue = issueService.updateIssue(issueId, issueDetails);
        return ResponseEntity.ok(updatedIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable(value = "id") Long issueId) throws ResourceNotFoundException {
        issueService.deleteIssue(issueId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public Page<Issue> findByStatus(@PathVariable(value = "status") String status, Pageable pageable) {
        return issueService.findByStatus(status, pageable);
    }

    @GetMapping("/reporter/{reporter}")
    public Page<Issue> findByReporter(@PathVariable(value = "reporter") String reporter, Pageable pageable) {
        return issueService.findByReporter(reporter, pageable);
    }

    @GetMapping("/assignee/{assignee}")
    public Page<Issue> findByAssignee(@PathVariable(value = "assignee") String assignee, Pageable pageable) {
        return issueService.findByAssignee(assignee, pageable);
    }
}
