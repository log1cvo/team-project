package com.example.issuemanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.issuemanager.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
    List<Comment> findByIssueId(Long issueId);
    List<Comment> findByAuthorId(Long authorId);
    List<Comment> findByIssueIdAndAuthorId(Long issueId, Long authorId);
}
