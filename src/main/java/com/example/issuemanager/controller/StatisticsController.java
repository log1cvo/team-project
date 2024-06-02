package com.example.issuemanager.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.service.IssueStatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private IssueStatisticsService issueStatisticsService;

    @GetMapping("/totalIssues")
    public ResponseEntity<Integer> getTotalIssues() {
        int totalIssues = issueStatisticsService.getTotalIssues();
        return ResponseEntity.ok(totalIssues);
    }

    @GetMapping("/resolvedIssues")
    public ResponseEntity<Integer> getResolvedIssues() {
        int resolvedIssues = issueStatisticsService.getResolvedIssues();
        return ResponseEntity.ok(resolvedIssues);
    }

    @GetMapping("/unresolvedIssues")
    public ResponseEntity<Integer> getUnresolvedIssues() {
        int unresolvedIssues = issueStatisticsService.getUnresolvedIssues();
        return ResponseEntity.ok(unresolvedIssues);
    }

    @GetMapping("/issuesByDateRange")
    public ResponseEntity<List<Issue>> getIssuesByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<Issue> issues = issueStatisticsService.getIssuesByDateRange(startDate, endDate);
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/issuesCountByMonth")
    public ResponseEntity<Long> getIssuesCountByMonth(@RequestParam int year, @RequestParam int month) {
        long count = issueStatisticsService.getIssuesCountByMonth(year, month);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/dailyIssues")
    public ResponseEntity<List<Issue>> getDailyIssues(@RequestParam Date date) {
        List<Issue> issues = issueStatisticsService.getDailyIssues(date);
        return ResponseEntity.ok(issues);
    }
}
