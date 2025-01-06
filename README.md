# Microservices Architecture Overview

This document provides an overview of the microservices architecture implemented, detailing the components, their interactions, and how they work together to deliver a scalable and efficient system.

![](microservices.png)
---

## System Architecture
The architecture includes the following key components:

### 1. **Eureka Server** (Service Discovery)
- **Purpose**: Keeps track of all client applications and their instances, including IP addresses and ports.
- **Key Features**:
    - Microservices register as clients with the Eureka server.
    - Allows dynamic lookup and connection between services.
- **Example**:
    - Customer Service running on port 8080.
    - Fraud Service running on port 8081.

### 2. **Microservices**
#### a. Customer Service
- Endpoint: `/customer`
- Port: `8080`

#### b. Fraud Service
- Endpoint: `/fraud`
- Port: `8081`

#### C. Notification Service
- Endpoint: `/notification`
- Port: `8082`

### 3. **API Gateway (APIGW)**
- Acts as the main entry point for clients.
- Performs API key validation and forwards requests to internal services.


### 4. **Message Queue (amqp)**
#### RabbitMQ
- **Purpose**: Enables asynchronous communication between services.
- **Components**:
    - **Producer**: Sends messages to the exchange.
    - **Exchange**: Routes messages to queues based on routing patterns.
    - **Consumer**: Processes messages from queues.

#### Example Flow:
1. Producer sends a message to the topic exchange.
2. Exchange forwards messages to queues (`App A Queue`, `App B Queue`, etc.) based on routing patterns.
3. Consumers (`App A`, `App B`, etc.) retrieve messages from their respective queues.

### 5. **Distributed Configuration**
#### Config Server
- **Purpose**: Provides environment configurations to microservices dynamically.
- **Features**:
    - Centralized management of configuration files.

### 6. **Distributed Tracing**
#### Zipkin
- **Purpose**: Provides tracing and monitoring capabilities across services.
- **Features**:
    - TraceID and SpanID for tracking requests.

### 7. **Load Balancer**
- Balances traffic between instances of the same service.
- Ensures high availability and fault tolerance.

### 8. **Private Docker Registry**
- Hosts Docker images used for deploying microservices.
- Microservices pull images from the registry during deployment.

### 9. **Postgres Database**
- Acts as the persistent storage layer for the services.

---

## Interaction Flow
### Service Discovery Example
1. **Register**:
    - Customer Service and Fraud Service register with Eureka Server.
2. **Lookup**:
    - Services dynamically discover the location of other services using Eureka.
3. **Connect**:
    - Customer Service interacts with Fraud Service over HTTP (e.g., port 8081).

### Asynchronous Communication
- Notifications are sent via RabbitMQ.
- Producers and consumers are bound to the message queue for efficient event-driven processing.

---

## Key Technologies
- **Spring Boot**: Microservices framework.
- **RabbitMQ & Kafka**: Asynchronous messaging.
- **Postgres**: Database.
- **Eureka**: Service discovery.
- **Zipkin**: Distributed tracing.
- **Docker & Kubernetes**: Containerization and orchestration.

---

## Notes
- This architecture ensures scalability, fault tolerance, and efficient resource utilization.
- Follow best practices for secure API management and distributed system design.
