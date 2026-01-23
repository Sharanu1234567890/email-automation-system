package com.email_scheduler.worker_service.repository;

import com.email_scheduler.worker_service.domain.EmailJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailJobRepository extends JpaRepository<EmailJob, Long> {
}
