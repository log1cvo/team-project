package com.example.issuemanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.issuemanager.model.Comment;
import com.example.issuemanager.service.CommentService;
import com.example.issuemanager.service.IssueService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private IssueService issueService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        if (issueService.getIssueById(comment.getIssue().getId()).isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public Optional<Comment> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        if (!commentService.getCommentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comment.setId(id);
        Comment updatedComment = commentService.updateComment(comment);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        if (!commentService.getCommentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
