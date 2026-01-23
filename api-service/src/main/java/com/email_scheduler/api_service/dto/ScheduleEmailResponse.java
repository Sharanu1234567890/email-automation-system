package com.email_scheduler.api_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleEmailResponse {
    private Long jobId;
    private String status;
}
