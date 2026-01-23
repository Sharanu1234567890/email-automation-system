package com.email_scheduler.worker_service.service;

import com.email_scheduler.worker_service.domain.EmailJob;
import com.email_scheduler.worker_service.domain.EmailRecipient;
import com.email_scheduler.worker_service.domain.EmailStatus;
import com.email_scheduler.worker_service.repository.EmailJobRepository;
import com.email_scheduler.worker_service.repository.EmailRecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailWorkerService {

    private final EmailJobRepository jobRepo;
    private final EmailRecipientRepository recipientRepo;
    private final EmailSenderService sender;

    private static final int MAX_RETRY = 3;

    @Transactional
    public void processJob(Long jobId) {

        EmailJob job = jobRepo.findById(jobId).orElseThrow();

        // ⏰ If scheduled time not reached → retry later
        if (job.getScheduledAt().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Job not scheduled yet");
        }

        List<EmailRecipient> recipients = recipientRepo.findByJobId(jobId);

        boolean allSent = true;

        for (EmailRecipient recipient : recipients) {

            if (recipient.getStatus() == EmailStatus.SENT) continue;

            boolean success = false;
            int attempts = recipient.getRetryCount();

            while (attempts < MAX_RETRY && !success) {
                success = sender.sendEmail(
                        recipient.getRecipientEmail(),
                        job.getSubject(),
                        job.getBody()
                );
                attempts++;
            }

            recipient.setRetryCount(attempts);
            recipient.setStatus(success ? EmailStatus.SENT : EmailStatus.FAILED);
            recipientRepo.save(recipient);

            if (!success) allSent = false;
        }

        job.setStatus(allSent ? EmailStatus.SENT : EmailStatus.PARTIAL_FAILED);
        jobRepo.save(job);
    }
}
