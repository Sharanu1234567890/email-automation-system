package com.email_scheduler.api_service.controller;


import com.email_scheduler.api_service.dto.ScheduleEmailRequest;
import com.email_scheduler.api_service.dto.ScheduleEmailResponse;
import com.email_scheduler.api_service.service.EmailSchedulerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailSchedulerService schedulerService;

    @PostMapping("/schedule")
    public ScheduleEmailResponse schedule(@Valid @RequestBody ScheduleEmailRequest request) {
        Long jobId = schedulerService.schedule(request);
        return new ScheduleEmailResponse(jobId, "SCHEDULED");
    }
}
