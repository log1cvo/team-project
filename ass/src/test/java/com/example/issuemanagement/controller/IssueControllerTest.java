package com.example.issuemanagement.controller;

import com.example.issuemanagement.model.Issue;
import com.example.issuemanagement.service.IssueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class IssueControllerTest {

    @Mock
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    private MockMvc mockMvc;

    @Test
    void testGetAllIssues() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
        when(issueService.getAllIssues()).thenReturn(Arrays.asList(new Issue(1L, "Sample Issue", "This is a sample issue.", "John Doe", "Jane Doe", "open")));

        mockMvc.perform(get("/api/issues")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Sample Issue"));
    }

    @Test
    void testCreateIssue() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
        Issue issue = new Issue(null, "New Issue", "This is a new issue.", "John Doe", "Jane Doe", "open");
        when(issueService.createIssue(any(Issue.class))).thenReturn(issue);

        mockMvc.perform(post("/api/issues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"New Issue\", \"description\": \"This is a new issue.\", \"reporter\": \"John Doe\", \"assignee\": \"Jane Doe\", \"status\": \"open\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Issue"));
    }

    @Test
    void testUpdateIssue() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
        Issue issue = new Issue(1L, "Updated Issue", "This is an updated issue.", "John Doe", "Jane Doe", "open");
        when(issueService.updateIssue(eq(1L), any(Issue.class))).thenReturn(issue);

        mockMvc.perform(put("/api/issues/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Issue\", \"description\": \"This is an updated issue.\", \"reporter\": \"John Doe\", \"assignee\": \"Jane Doe\", \"status\": \"open\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Issue"));
    }

    @Test
    void testDeleteIssue() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
        doNothing().when(issueService).deleteIssue(1L);

        mockMvc.perform(delete("/api/issues/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
