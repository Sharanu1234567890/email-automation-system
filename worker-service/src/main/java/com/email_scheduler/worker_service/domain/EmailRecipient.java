package com.email_scheduler.worker_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmailRecipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private String recipientEmail;

    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    private int retryCount = 0;
}
