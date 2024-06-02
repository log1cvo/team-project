package com.example.issuemanager.service;

import com.example.issuemanager.model.ResolvedIssueHistory;
import com.example.issuemanager.model.User;
import com.example.issuemanager.repository.ResolvedIssueHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AssigneeRecommendationService {

    private final ResolvedIssueHistoryRepository resolvedIssueHistoryRepository;

    @Autowired
    public AssigneeRecommendationService(ResolvedIssueHistoryRepository resolvedIssueHistoryRepository) {
        this.resolvedIssueHistoryRepository = resolvedIssueHistoryRepository;
    }

    public Optional<User> recommendAssignee() {
        List<ResolvedIssueHistory> historyList = resolvedIssueHistoryRepository.findAll();
        Map<User, Long> userCounts = new HashMap<>();

        for (ResolvedIssueHistory history : historyList) {
            User resolver = history.getResolver();
            userCounts.put(resolver, userCounts.getOrDefault(resolver, 0L) + 1);
        }

        return userCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
