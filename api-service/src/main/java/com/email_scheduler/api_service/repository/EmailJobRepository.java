package com.email_scheduler.api_service.repository;


import com.email_scheduler.api_service.domain.EmailJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailJobRepository extends JpaRepository<EmailJob, Long> {
}
