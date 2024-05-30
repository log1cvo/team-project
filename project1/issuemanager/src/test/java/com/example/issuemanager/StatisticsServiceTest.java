package com.example.issuemanager;

import com.example.issuemanager.model.Issue;
import com.example.issuemanager.repository.IssueRepository;
import com.example.issuemanager.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class StatisticsServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetIssueCountByDay() {
        Issue issue1 = new Issue();
        issue1.setReportedDate(Date.from(LocalDate.of(2023, 5, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Issue issue2 = new Issue();
        issue2.setReportedDate(Date.from(LocalDate.of(2023, 5, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Issue issue3 = new Issue();
        issue3.setReportedDate(Date.from(LocalDate.of(2023, 5, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        List<Issue> issues = Arrays.asList(issue1, issue2, issue3);

        when(issueRepository.findAll()).thenReturn(issues);

        Map<String, Long> result = statisticsService.getIssueCountByDay();

        assertThat(result.get("2023-05-01")).isEqualTo(2);
        assertThat(result.get("2023-05-02")).isEqualTo(1);
    }

    @Test
    public void testGetIssueCountByMonth() {
        Issue issue1 = new Issue();
        issue1.setReportedDate(Date.from(LocalDate.of(2023, 5, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Issue issue2 = new Issue();
        issue2.setReportedDate(Date.from(LocalDate.of(2023, 5, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Issue issue3 = new Issue();
        issue3.setReportedDate(Date.from(LocalDate.of(2023, 6, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        List<Issue> issues = Arrays.asList(issue1, issue2, issue3);

        when(issueRepository.findAll()).thenReturn(issues);

        Map<String, Long> result = statisticsService.getIssueCountByMonth();

        assertThat(result.get("2023-5")).isEqualTo(2);
        assertThat(result.get("2023-6")).isEqualTo(1);
    }

    @Test
    public void testGetIssueCountByYear() {
        Issue issue1 = new Issue();
        issue1.setReportedDate(Date.from(LocalDate.of(2022, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Issue issue2 = new Issue();
        issue2.setReportedDate(Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Issue issue3 = new Issue();
        issue3.setReportedDate(Date.from(LocalDate.of(2023, 6, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        List<Issue> issues = Arrays.asList(issue1, issue2, issue3);

        when(issueRepository.findAll()).thenReturn(issues);

        Map<String, Long> result = statisticsService.getIssueCountByYear();

        assertThat(result.get("2022")).isEqualTo(1);
        assertThat(result.get("2023")).isEqualTo(2);
    }

    @Test
    public void testGetIssueResolutionRate() {
        Issue issue1 = new Issue();
        issue1.setStatus("resolved");

        Issue issue2 = new Issue();
        issue2.setStatus("closed");

        Issue issue3 = new Issue();
        issue3.setStatus("new");

        List<Issue> issues = Arrays.asList(issue1, issue2, issue3);

        when(issueRepository.findAll()).thenReturn(issues);

        double result = statisticsService.getIssueResolutionRate();

        assertThat(result).isEqualTo(2.0 / 3.0);
    }
}
