package com.email_scheduler.api_service.repository;


import com.email_scheduler.api_service.domain.EmailRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRecipientRepository extends JpaRepository<EmailRecipient, Long> {
}
