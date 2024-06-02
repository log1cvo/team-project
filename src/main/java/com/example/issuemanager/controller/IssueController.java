package com.example.issuemanager.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.model.User;
import com.example.issuemanager.service.AssigneeRecommendationService;
import com.example.issuemanager.service.IssueService;
@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private AssigneeRecommendationService assigneeRecommendationService;

    @PostMapping
    public Issue createIssue(@RequestBody Issue issue) {
        // Automatically set the reporter and reported date
        issue.setReportedDate(new Date());
        return issueService.createIssue(issue);
    }

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/{id}")
    public Optional<Issue> getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long id, @RequestBody Issue issue) {
        if (!issueService.getIssueById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        issue.setId(id);
        Issue updatedIssue = issueService.updateIssue(issue);
        return ResponseEntity.ok(updatedIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        if (!issueService.getIssueById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        issueService.deleteIssue(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Issue> searchIssues(@RequestParam(required = false) String assignee,
                                    @RequestParam(required = false) String status,
                                    @RequestParam(required = false) String reporter) {
        return issueService.searchIssues(assignee, status, reporter);
    }

    @GetMapping("/recommendAssignee")
    public ResponseEntity<User> recommendAssignee() {
        return assigneeRecommendationService.recommendAssignee()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
