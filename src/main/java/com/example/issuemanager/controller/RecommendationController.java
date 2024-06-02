package com.example.issuemanager.controller;

import com.example.issuemanager.model.User;
import com.example.issuemanager.service.AssigneeRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private AssigneeRecommendationService assigneeRecommendationService;

    @GetMapping("/recommendAssignee")
    public ResponseEntity<User> recommendAssignee() {
        return assigneeRecommendationService.recommendAssignee()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
