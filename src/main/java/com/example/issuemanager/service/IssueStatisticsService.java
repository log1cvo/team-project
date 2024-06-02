package com.example.issuemanager.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.repository.IssueRepository;

@Service
public class IssueStatisticsService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueStatisticsService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public int getTotalIssues() {
        return issueRepository.findAll().size();
    }

    public int getResolvedIssues() {
        return (int) issueRepository.findAll().stream()
                .filter(issue -> "RESOLVED".equals(issue.getStatus()))
                .count();
    }

    public int getUnresolvedIssues() {
        return getTotalIssues() - getResolvedIssues();
    }

    public List<Issue> getIssuesByDateRange(Date startDate, Date endDate) {
        return issueRepository.findAll().stream()
                .filter(issue -> !issue.getReportedDate().before(startDate) && !issue.getReportedDate().after(endDate))
                .collect(Collectors.toList());
    }

    public long getIssuesCountByMonth(int year, int month) {
        return issueRepository.findAll().stream()
                .filter(issue -> {
                    Date reportedDate = issue.getReportedDate();
                    return reportedDate.getYear() + 1900 == year && reportedDate.getMonth() + 1 == month;
                })
                .count();
    }

    public List<Issue> getDailyIssues(Date date) {
        return issueRepository.findAll().stream()
                .filter(issue -> issue.getReportedDate().equals(date))
                .collect(Collectors.toList());
    }
}
