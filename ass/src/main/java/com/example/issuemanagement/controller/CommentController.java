package com.example.issuemanagement.controller;

import com.example.issuemanagement.exception.ResourceNotFoundException;
import com.example.issuemanagement.model.Comment;
import com.example.issuemanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/issues/{issueId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment createComment(@PathVariable(value = "issueId") Long issueId,
                                 @Valid @RequestBody Comment comment) throws ResourceNotFoundException {
        return commentService.createComment(issueId, comment);
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable(value = "issueId") Long issueId,
                                  @PathVariable(value = "id") Long commentId) throws ResourceNotFoundException {
        return commentService.getCommentById(issueId, commentId);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable(value = "issueId") Long issueId,
                                 @PathVariable(value = "id") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) throws ResourceNotFoundException {
        return commentService.updateComment(issueId, commentId, commentRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "issueId") Long issueId,
                                           @PathVariable(value = "id") Long commentId) throws ResourceNotFoundException {
        commentService.deleteComment(issueId, commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Comment> getCommentsByIssueId(@PathVariable(value = "issueId") Long issueId) throws ResourceNotFoundException {
        return commentService.getCommentsByIssueId(issueId);
    }
}
