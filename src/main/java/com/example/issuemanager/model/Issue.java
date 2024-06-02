    package com.example.issuemanager.model;

    import javax.persistence.*;
    import java.util.Date;
    import java.util.List;

    @Entity
    public class Issue {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String description;
        private String reporter;
        private Date reportedDate;
        private String fixer;
        private String assignee;
        private String priority;
        private String status;
        private Date resolvedDate;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user; // 添加此属性

        @ManyToOne
        @JoinColumn(name = "project_id")
        private Project project;

        @ElementCollection
        private List<String> comments;

        // Getters and Setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getReporter() {
            return reporter;
        }

        public void setReporter(String reporter) {
            this.reporter = reporter;
        }

        public Date getReportedDate() {
            return reportedDate;
        }

        public void setReportedDate(Date reportedDate) {
            this.reportedDate = reportedDate;
        }

        public String getFixer() {
            return fixer;
        }

        public void setFixer(String fixer) {
            this.fixer = fixer;
        }

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Date getResolvedDate() {
            return resolvedDate;
        }

        public void setResolvedDate(Date resolvedDate) {
            this.resolvedDate = resolvedDate;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Project getProject() {
            return project;
        }

        public void setProject(Project project) {
            this.project = project;
        }

        public List<String> getComments() {
            return comments;
        }

        public void setComments(List<String> comments) {
            this.comments = comments;
        }
    }
