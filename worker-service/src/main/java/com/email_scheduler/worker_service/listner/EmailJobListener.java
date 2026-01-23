package com.email_scheduler.worker_service.listner;

import com.email_scheduler.worker_service.config.RabbitMQConfig;
import com.email_scheduler.worker_service.service.EmailWorkerService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailJobListener {

    private final EmailWorkerService workerService;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void handleEmailJob(Long jobId) {
        System.out.println("Received job: " + jobId);
        workerService.processJob(jobId);
    }

}

