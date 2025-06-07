package com.example.appointmentservice.kafka;

import com.example.appointmentservice.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String APPOINTMENT_SCHEDULED_TOPIC = "patient-created";
    private static final String PATIENT_CREATED_TOPIC = "patient-created";

    // Function to publish Appointment scheduled event
    public void sendAppointmentScheduledEvent(Appointment appointment) {
        kafkaTemplate.send(APPOINTMENT_SCHEDULED_TOPIC, appointment.toString());
        logger.info("Published Appointment Scheduled Event: {}", appointment);
    }

    // Function to publish Patient Created event (for demonstration)
    public void sendPatientCreatedEvent(String patientId) {
        String message = "New patient created: Patient ID: " + patientId;
        kafkaTemplate.send(PATIENT_CREATED_TOPIC, message);

        // Log the published message
        logger.info("Published Patient Created Event: {}", message);
    }
}
