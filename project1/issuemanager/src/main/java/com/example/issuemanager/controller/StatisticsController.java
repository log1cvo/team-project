package com.example.issuemanager.controller;

import com.example.issuemanager.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/daily")
    public Map<String, Long> getIssueCountByDay() {
        return statisticsService.getIssueCountByDay();
    }

    @GetMapping("/monthly")
    public Map<String, Long> getIssueCountByMonth() {
        return statisticsService.getIssueCountByMonth();
    }

    @GetMapping("/yearly")
    public Map<String, Long> getIssueCountByYear() {
        return statisticsService.getIssueCountByYear();
    }

    @GetMapping("/resolution-rate")
    public double getIssueResolutionRate() {
        return statisticsService.getIssueResolutionRate();
    }
}
