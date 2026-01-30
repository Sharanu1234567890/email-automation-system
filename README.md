#  Apache Kafka – Producer & Consumer Demo

This project demonstrates how **Apache Kafka stores data** in a topic and allows a **consumer to start later** and still read previously produced messages.

It clearly shows Kafka’s **durability, offset management, and decoupled architecture**.

---

##  What This Project Shows

* Producer sends messages to Kafka
* Kafka **stores messages in a topic**
* Consumer can be started **later**
* Consumer reads **old + new messages**
* No message loss 

---

## Tech Stack

* Java 17
* Apache Kafka
* Zookeeper
* Docker & Docker Compose

---

##  Project Architecture

```
Producer  --->  Kafka Topic  --->  Consumer
                    |
               Stored Messages
```

Kafka retains messages even if consumers are offline.

---

##  How It Works

1. **Producer starts**

   * Sends messages to a Kafka topic
2. **Kafka stores messages**

   * Messages are persisted on disk
3. **Consumer starts later**

   * Reads messages from the beginning (based on offset)

---

## 🐳 Run Using Docker

### 1️ Start Kafka & Zookeeper

```bash
docker-compose up -d
```

---

### 2️ Run Producer

```bash
java ProducerApp
```

Example Output:

```
Sent: Hello Kafka
Sent: Message 1
Sent: Message 2
```

---

### 3️ Start Consumer (Later)

```bash
java ConsumerApp
```

Example Output:

```
Received: Hello Kafka
Received: Message 1
Received: Message 2
```

Even though the consumer started later, **all messages were received**.

---

## ⚙️ Key Kafka Configuration

```properties
auto.offset.reset=earliest
enable.auto.commit=true
```

This allows the consumer to read messages from the start of the topic.

---

##  Why Kafka

* Producers and consumers are **fully decoupled**
* Messages are **durable and persistent**
* Consumers can **replay data anytime**
* Built for **high-throughput systems**

---

##  Common Use Cases

* Event-driven architectures
* Microservices communication
* Log processing
* Real-time data pipelines

---

##  Tested Environment

* Java 17
* Apache Kafka 3.x
* Docker

---

## 📎 Author

**Sharanu**
AI & ML Engineering Student
Java | Kafka | Backend Systems
