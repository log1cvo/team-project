package com.example.issuemanager.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.issuemanager.service.IssueService;

@Service
public class IssueStatistics {

    @Autowired
    private IssueService issueService;

    public int getTotalIssues() {
        return issueService.getAllIssues().size();
    }

    public int getResolvedIssues() {
        return (int) issueService.getAllIssues().stream().filter(issue -> issue.getStatus().equals("RESOLVED")).count();
    }

    public int getUnresolvedIssues() {
        return getTotalIssues() - getResolvedIssues();
    }
}