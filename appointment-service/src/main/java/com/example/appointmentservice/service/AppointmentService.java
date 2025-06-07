package com.example.appointmentservice.service;

import com.example.appointmentservice.kafka.KafkaProducerService;
import com.example.appointmentservice.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);


    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public AppointmentService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    public String scheduleAppointment(Appointment appointment) {
        // Simulate saving the appointment (you can save it to the database)
        logger.info("Scheduling Appointment: {}", appointment);

        // Publish the event to Kafka
        kafkaProducerService.sendAppointmentScheduledEvent(appointment);

        // Return response
        return "Appointment Scheduled";
    }
}