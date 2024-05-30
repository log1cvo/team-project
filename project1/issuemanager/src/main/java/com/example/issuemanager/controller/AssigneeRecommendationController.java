package com.example.issuemanager.controller;

import com.example.issuemanager.service.AssigneeRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class AssigneeRecommendationController {

    @Autowired
    private AssigneeRecommendationService recommendationService;

    @GetMapping("/assignees")
    public List<String> recommendAssignees() {
        return recommendationService.recommendAssignees();
    }
}
