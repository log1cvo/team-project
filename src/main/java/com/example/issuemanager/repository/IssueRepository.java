package com.example.issuemanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.issuemanager.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<Issue> findById(Long id);
    List<Issue> findByAssignee(String assignee);
    List<Issue> findByStatus(String status);
    List<Issue> findByReporter(String reporter);
    List<Issue> findByAssigneeAndStatus(String assignee, String status);
    List<Issue> findByAssigneeAndReporter(String assignee, String reporter);
    List<Issue> findByStatusAndReporter(String status, String reporter);
    List<Issue> findByAssigneeAndStatusAndReporter(String assignee, String status, String reporter);
}
