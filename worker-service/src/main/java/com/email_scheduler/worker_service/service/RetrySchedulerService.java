//package com.email_scheduler.worker_service.service;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class RetrySchedulerService {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public void retryLater(Long jobId) {
//        rabbitTemplate.convertAndSend(
//                "email.exchange",
//                "email.job",
//                jobId
//        );
//    }
//}
