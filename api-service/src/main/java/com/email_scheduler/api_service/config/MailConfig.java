package com.email_scheduler.api_service.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {
    // API does NOT send emails directly
    // Worker-service handles SMTP
}
