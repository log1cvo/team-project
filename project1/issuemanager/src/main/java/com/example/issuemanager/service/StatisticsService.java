package com.example.issuemanager.service;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private IssueRepository issueRepository;

    public Map<String, Long> getIssueCountByDay() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream()
                .collect(Collectors.groupingBy(
                        issue -> issue.getReportedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString(),
                        Collectors.counting()
                ));
    }

    public Map<String, Long> getIssueCountByMonth() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream()
                .collect(Collectors.groupingBy(
                        issue -> {
                            LocalDate date = issue.getReportedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            return date.getYear() + "-" + date.getMonthValue();
                        },
                        Collectors.counting()
                ));
    }

    public Map<String, Long> getIssueCountByYear() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream()
                .collect(Collectors.groupingBy(
                        issue -> {
                            LocalDate date = issue.getReportedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            return String.valueOf(date.getYear());
                        },
                        Collectors.counting()
                ));
    }

    public double getIssueResolutionRate() {
        List<Issue> issues = issueRepository.findAll();
        long totalIssues = issues.size();
        long resolvedIssues = issues.stream()
                .filter(issue -> "resolved".equals(issue.getStatus()) || "closed".equals(issue.getStatus()))
                .count();
        return totalIssues == 0 ? 0 : (double) resolvedIssues / totalIssues;
    }
}
