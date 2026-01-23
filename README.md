# Email Scheduler – Distributed Worker System

## Overview(By Reading only you will master the project)

This project is a **production-grade, distributed email scheduling system** built using **Spring Boot microservices**, **RabbitMQ**, **MySQL**, **Redis**, and **Docker**. It demonstrates how to design and implement an **asynchronous, fault-tolerant worker architecture** commonly used in real-world backend and platform engineering teams.

The system decouples request handling from email delivery using a message broker, ensuring **scalability, reliability, and non-blocking API performance**.

This repository contains two independently deployable services:

* **API Service** – Accepts email scheduling requests and publishes jobs
* **Worker Service** – Consumes jobs, sends emails via SMTP, handles retries, and updates state

---

## High-Level Architecture

```
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

### Key Design Principles

* **Asynchronous processing** to avoid blocking API threads
* **Loose coupling** between producer and consumer
* **At-least-once delivery** with retry support
* **Horizontal scalability** for worker services
* **Failure isolation** (email failures do not impact API uptime)

---

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

## Service Responsibilities

### API Service

* Exposes REST endpoints to schedule emails
* Validates and persists email metadata
* Publishes email jobs to RabbitMQ
* Remains stateless and fast under load

### Worker Service

* Listens to RabbitMQ queues
* Processes email jobs asynchronously
* Sends emails using JavaMailSender
* Implements retry logic for transient failures
* Updates email status in MySQL (PENDING → SENT / FAILED)

---

## Message Flow

1. Client sends request to API Service
2. API Service validates request and saves metadata
3. Email job is published to RabbitMQ
4. Worker Service consumes the message
5. Email is sent via SMTP
6. Status is updated in MySQL
7. Failures are retried based on configuration

---

## Reliability & Failure Handling

* **Retry mechanism** for SMTP/network failures
* **Idempotent processing** to avoid duplicate sends
* **Dead-letter ready design** (can be enabled easily)
* **Health checks** for MySQL and RabbitMQ
* **Stateless workers** allow safe restarts and scaling

---

## Configuration & Secrets

Sensitive credentials (DB, SMTP, RabbitMQ) are externalized via environment variables and injected at runtime. No secrets are hardcoded in the application.

SMTP uses **Gmail App Passwords**, following best security practices.

---

## Running the System

### Prerequisites

* Docker & Docker Compose
* Gmail account with App Password enabled

### Start All Services

```bash
docker compose up --build
```

### Access

* API Service: `http://localhost:8080`
* RabbitMQ Management UI: `http://localhost:15672`

---

## Scalability Considerations

* Multiple **worker-service replicas** can consume from the same queue
* API service can be scaled independently
* RabbitMQ ensures load distribution across workers
* Database writes are isolated from request handling

---

## Why This Design

This architecture mirrors **real-world backend systems** used for:

* Email delivery platforms
* Notification systems
* Payment processing pipelines
* Background job execution frameworks

It prioritizes **system stability, throughput, and maintainability** over synchronous simplicity.

---

## Future Enhancements

* Dead Letter Queues (DLQ)
* Rate limiting per recipient
* Observability (Prometheus + Grafana)
* Distributed tracing
* Support for multiple email providers

---

## Author

Built as a backend-focused, production-oriented project demonstrating real-world system design and microservice communication patterns.

---

## License

MIT License
