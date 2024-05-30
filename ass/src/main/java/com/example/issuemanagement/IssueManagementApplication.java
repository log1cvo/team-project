package com.example.issuemanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Issue Management API", version = "1.0", description = "API for managing issues"))
public class IssueManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(IssueManagementApplication.class, args);
    }
}
