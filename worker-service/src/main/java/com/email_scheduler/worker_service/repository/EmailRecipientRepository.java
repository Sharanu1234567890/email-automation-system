package com.email_scheduler.worker_service.repository;

import com.email_scheduler.worker_service.domain.EmailRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRecipientRepository extends JpaRepository<EmailRecipient, Long> {
    List<EmailRecipient> findByJobId(Long jobId);
}
