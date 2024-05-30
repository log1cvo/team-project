package com.example.issuemanager;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.repository.IssueRepository;
import com.example.issuemanager.service.AssigneeRecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AssigneeRecommendationServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private AssigneeRecommendationService recommendationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecommendAssignees() {
        Issue issue1 = new Issue();
        issue1.setFixer("user1");
        issue1.setStatus("resolved");

        Issue issue2 = new Issue();
        issue2.setFixer("user2");
        issue2.setStatus("closed");

        Issue issue3 = new Issue();
        issue3.setFixer("user1");
        issue3.setStatus("resolved");

        Issue issue4 = new Issue();
        issue4.setFixer("user3");
        issue4.setStatus("closed");

        Issue issue5 = new Issue();
        issue5.setFixer("user2");
        issue5.setStatus("resolved");

        List<Issue> issues = Arrays.asList(issue1, issue2, issue3, issue4, issue5);

        when(issueRepository.findAll()).thenReturn(issues);

        List<String> recommendedAssignees = recommendationService.recommendAssignees();

        assertThat(recommendedAssignees).containsExactly("user1", "user2", "user3");
    }
}
