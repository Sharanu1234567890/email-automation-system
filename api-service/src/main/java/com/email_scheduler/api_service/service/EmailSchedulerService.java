package com.email_scheduler.api_service.service;

import com.email_scheduler.api_service.config.RabbitMQConfig;
import com.email_scheduler.api_service.domain.EmailJob;
import com.email_scheduler.api_service.domain.EmailRecipient;
import com.email_scheduler.api_service.domain.EmailStatus;
import com.email_scheduler.api_service.dto.ScheduleEmailRequest;
import com.email_scheduler.api_service.repository.EmailJobRepository;
import com.email_scheduler.api_service.repository.EmailRecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailSchedulerService {

    private final EmailJobRepository jobRepository;
    private final EmailRecipientRepository recipientRepository;
    private final RabbitTemplate rabbitTemplate;

    public Long schedule(ScheduleEmailRequest request) {

        EmailJob job = new EmailJob();
        job.setUserId(request.getUserId());
        job.setSubject(request.getSubject());
        job.setBody(request.getBody());
        job.setScheduledAt(request.getScheduledAt());
        job.setStatus(EmailStatus.PENDING);

        job = jobRepository.save(job);

        for (String email : request.getRecipients()) {
            EmailRecipient recipient = new EmailRecipient();
            recipient.setJobId(job.getId());
            recipient.setRecipientEmail(email);
            recipient.setStatus(EmailStatus.PENDING);
            recipientRepository.save(recipient);
        }

        // rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, job.getId());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EMAIL_EXCHANGE,
                RabbitMQConfig.EMAIL_ROUTING_KEY,
                job.getId(),
                message -> {
                    long delayMillis = Duration.between(LocalDateTime.now(), request.getScheduledAt()).toMillis();
                    if (delayMillis > 0) {
                        message.getMessageProperties().getHeaders().put("x-delay", delayMillis);
                    }
                    return message;
                }
        );



        return job.getId();
    }
}
