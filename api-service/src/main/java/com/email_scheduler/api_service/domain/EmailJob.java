package com.email_scheduler.api_service.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    private LocalDateTime createdAt = LocalDateTime.now();
}
