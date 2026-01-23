##Email Scheduler System
Client
  │
  ▼
API Service (Spring Boot)
  │  ── Publish Message
  ▼
RabbitMQ (Queue)
  │  ── Consume Message
  ▼
Worker Service (Spring Boot)
  │
  ├─ SMTP (Email Delivery)
  ├─ MySQL (Persistent State)
  └─ Redis (Optional caching / idempotency)
```


## Tech Stack

| Layer            | Technology                |
| ---------------- | ------------------------- |
| Language         | Java 17                   |
| Framework        | Spring Boot               |
| Messaging        | RabbitMQ                  |
| Database         | MySQL 8                   |
| Cache            | Redis                     |
| Email            | SMTP (Gmail App Password) |
| Containerization | Docker & Docker Compose   |

---


Overview

Email Scheduler System is a distributed backend system that allows users to schedule emails for a future time and ensures that the emails are sent automatically at the scheduled time.

The system is built using Spring Boot microservices, RabbitMQ for asynchronous processing, and MySQL for persistent storage.

High-Level Architecture
flowchart LR
    Client[Client / Frontend]
    API[API Service]
    DB[(MySQL)]
    MQ[RabbitMQ]
    Worker[Worker Service]
    SMTP[SMTP Server]

    Client -->|REST API| API
    API -->|Persist Job| DB
    API -->|Publish Job ID| MQ
    MQ -->|Consume Job| Worker
    Worker -->|Fetch Job| DB
    Worker -->|Send Email| SMTP
    Worker -->|Update Status| DB


Diagram renders directly on GitHub.

Clear separation of responsibilities.

Designed for horizontal scaling.

Services
API Service

Responsibilities:

Accept email scheduling requests via REST API

Validate payloads

Persist jobs and recipients in MySQL

Publish job ID to RabbitMQ

Does NOT:

Send emails

Perform heavy processing

Worker Service

Responsibilities:

Consume scheduled jobs from RabbitMQ

Send emails using SMTP

Retry on failure (up to 3 times)

Update job and recipient status in MySQL

Data Model
EmailJob
Field	Description
id	Job identifier
userId	User who scheduled the job
subject	Email subject
body	Email body
scheduledAt	Scheduled execution time
status	PENDING / SENT / PARTIAL_FAILED
EmailRecipient
Field	Description
id	Recipient identifier
jobId	Parent job
recipientEmail	Recipient email address
status	PENDING / SENT / FAILED
retryCount	Retry attempts
Message Flow

Client schedules an email via REST API.

API Service:

Persists job in MySQL

Publishes job ID to RabbitMQ

Worker Service:

Consumes job

Sends email at scheduled time

Updates delivery status in MySQL

Technology Stack
Layer	Technology
Language	Java 17
Framework	Spring Boot 3.x
Messaging	RabbitMQ
Database	MySQL 8
Cache	Redis
Email	SMTP (Gmail App Password)
Containerization	Docker, Docker Compose
Running Locally
docker compose up --build

Exposed Services
Service	URL
API Service	http://localhost:8080

RabbitMQ UI	http://localhost:15672

MySQL	localhost:3307
Reliability Features

Email jobs persisted before execution

Retry logic for failed emails

Partial failures tracked per recipient

Scalable architecture: Worker service can be scaled horizontally

Production Readiness Notes

Stateless API service

Asynchronous message-driven execution

Idempotent worker processing

Dockerized for deployment

Future Enhancements

Dead Letter Queue (DLQ)

Rate limiting per user

Timezone support

Email templates

Admin dashboard for monitoring
MIT License
