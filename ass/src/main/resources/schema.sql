CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS issues (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(100) NOT NULL,
    description TEXT,
    reporter VARCHAR(50),
    assignee VARCHAR(50),
    status VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS comments (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        issue_id BIGINT,
                                        text TEXT,
                                        author VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (issue_id) REFERENCES issues(id)
    );
