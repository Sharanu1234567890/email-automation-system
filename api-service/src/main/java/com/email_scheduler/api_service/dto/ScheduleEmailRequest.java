package com.email_scheduler.api_service.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScheduleEmailRequest {

    @NotNull
    private Long userId;

    @NotEmpty
    private String subject;

    @NotEmpty
    private String body;

    @NotEmpty
    private List<String> recipients;

    @NotNull
    private LocalDateTime scheduledAt;
}
