package com.example.appointmentservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);


    // Consuming messages from the 'appointment-scheduled' topic
    @KafkaListener(topics = "appointment-scheduled", groupId = "appointment_service_group")
    public void listenAppointmentScheduled(String message) {
        logger.info("Received Appointment Scheduled Event: {}", message);
    }

    // Consuming messages from the 'patient-created' topic
    @KafkaListener(topics = "patient-created", groupId = "appointment_service_group")
    public void listenPatientCreated(String message) {
        logger.info("Received Patient Created Event: {}", message);
    }
}
