package com.example.issuemanagement.repository;

import com.example.issuemanagement.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Page<Issue> findByStatus(String status, Pageable pageable);
    Page<Issue> findByReporter(String reporter, Pageable pageable);
    Page<Issue> findByAssignee(String assignee, Pageable pageable);
    Optional<Issue> findByIdAndReporter(Long id, String reporter);
}
