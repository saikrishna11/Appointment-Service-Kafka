package com.example.appointmentservice.controller;

import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.service.AppointmentService;
import com.example.appointmentservice.kafka.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, KafkaProducerService kafkaProducerService) {
        this.appointmentService = appointmentService;
        this.kafkaProducerService = kafkaProducerService;
    }

    // Endpoint to schedule an appointment
    @PostMapping
    public String scheduleAppointment(@RequestBody Appointment appointment) {
        // Log the appointment scheduling request
        logger.info("Scheduling Appointment: {}", appointment);

        // Schedule the appointment
        String response = appointmentService.scheduleAppointment(appointment);

        // Publish the appointment scheduled event to Kafka
        kafkaProducerService.sendAppointmentScheduledEvent(appointment);

        return response;
    }
}
