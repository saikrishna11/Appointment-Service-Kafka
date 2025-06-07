# appointment-service-kafka

This is an Appointment Scheduling Microservice with Kafka integration in the healthcare domain.

## Features

1. **Appointment Scheduling**:
    - Provides REST endpoints to schedule appointments.
    - Publishes an event to Kafka when an appointment is scheduled.

2. **Kafka Integration**:
    - **Producer**:
        - Publishes messages to the `appointment-scheduled` topic when an appointment is scheduled.
        - Publishes messages to the `patient-created` topic when a new patient is created.
    - **Consumer**:
        - Listens to the `appointment-scheduled` topic to process scheduled appointment events.
        - Listens to the `patient-created` topic to verify or process patient data before scheduling an appointment.

3. **Spring Boot Integration**:
    - Built using Spring Boot for rapid development and ease of integration with Kafka.

## How It Works

1. **Appointment Scheduling**:
    - A REST API endpoint accepts appointment details.
    - The service schedules the appointment and publishes an event to Kafka.

2. **Kafka Producer**:
    - Sends messages to Kafka topics (`appointment-scheduled` and `patient-created`) with relevant event details.

3. **Kafka Consumer**:
    - Listens to Kafka topics and processes the incoming messages.

## Endpoints

- **POST /appointments**: Schedule an appointment.
    - Request Body:
      ```json
      {
        "patientId": "123",
        "doctor": "Dr. Smith",
        "date": "2023-10-01",
        "time": "10:00 AM"
      }
      ```
    - Response: A confirmation message.

## Kafka Topics

- **appointment-scheduled**: Used to publish and consume appointment scheduling events.
- **patient-created**: Used to publish and consume patient creation events.

## Prerequisites

- Java 11 or higher
- Maven
- Kafka server running locally or remotely

## Build and Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd appointment-service-kafka