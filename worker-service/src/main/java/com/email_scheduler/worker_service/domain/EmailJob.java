package com.email_scheduler.worker_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EmailJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String subject;
    private String body;

    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    private EmailStatus status;
}
