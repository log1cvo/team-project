package com.example.issuemanagement.service;

import com.example.issuemanagement.exception.ResourceNotFoundException;
import com.example.issuemanagement.model.Comment;
import com.example.issuemanagement.model.Issue;
import com.example.issuemanagement.repository.CommentRepository;
import com.example.issuemanagement.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    public Comment createComment(Long issueId, Comment comment) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Issue", "id", issueId));

        comment.setIssue(issue);
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long issueId, Long commentId) {
        if (!issueRepository.existsById(issueId)) {
            throw new ResourceNotFoundException("Issue", "id", issueId);
        }

        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
    }

    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }

    public Comment updateComment(Long issueId, Long commentId, Comment commentDetails) {
        if (!issueRepository.existsById(issueId)) {
            throw new ResourceNotFoundException("Issue", "id", issueId);
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        comment.setText(commentDetails.getText());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long issueId, Long commentId) {
        if (!issueRepository.existsById(issueId)) {
            throw new ResourceNotFoundException("Issue", "id", issueId);
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        commentRepository.delete(comment);
    }
}
