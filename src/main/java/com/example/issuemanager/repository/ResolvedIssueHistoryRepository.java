package com.example.issuemanager.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.issuemanager.model.ResolvedIssueHistory;

@Repository
public interface ResolvedIssueHistoryRepository extends JpaRepository<ResolvedIssueHistory, Long> {
    Optional<ResolvedIssueHistory> findById(Long id);
    List<ResolvedIssueHistory> findByResolverId(Long resolverId);
    List<ResolvedIssueHistory> findByIssueId(Long issueId);
    List<ResolvedIssueHistory> findByResolvedDateBetween(Date startDate, Date endDate);
    List<ResolvedIssueHistory> findByIssueIdAndResolverId(Long issueId, Long resolverId);
}
