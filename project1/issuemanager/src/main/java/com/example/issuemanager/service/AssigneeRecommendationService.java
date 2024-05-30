package com.example.issuemanager.service;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssigneeRecommendationService {

    @Autowired
    private IssueRepository issueRepository;

    public List<String> recommendAssignees() {
        List<Issue> resolvedIssues = issueRepository.findAll().stream()
                .filter(issue -> "resolved".equals(issue.getStatus()) || "closed".equals(issue.getStatus()))
                .collect(Collectors.toList());

        Map<String, Long> assigneeCount = resolvedIssues.stream()
                .collect(Collectors.groupingBy(Issue::getFixer, Collectors.counting()));

        return assigneeCount.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
    }
}
