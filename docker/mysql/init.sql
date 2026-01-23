CREATE DATABASE IF NOT EXISTS emailscheduler;
USE emailscheduler;

CREATE TABLE email_job (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    subject VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    scheduled_at DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE email_recipient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_id BIGINT NOT NULL,
    recipient_email VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    sent_at DATETIME,
    FOREIGN KEY (job_id) REFERENCES email_job(id) ON DELETE CASCADE
);

CREATE INDEX idx_job_status ON email_job(status);
CREATE INDEX idx_job_schedule ON email_job(scheduled_at);
CREATE INDEX idx_recipient_status ON email_recipient(status);
