package com.example.appointmentservice.service;

import com.example.appointmentservice.kafka.KafkaProducerService;
import com.example.appointmentservice.model.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void testScheduleAppointment() {
        String patientId = "123";
        String doctor = "Dr. Smith";
        String appointmentDate = "2025-07-10";
        String appointmentTime = "10:00";

        Appointment appointment = new Appointment(patientId, doctor, appointmentDate , appointmentTime);

        String response = appointmentService.scheduleAppointment(appointment);
        // Verify if the KafkaProducerService's sendAppointmentScheduledEvent was called once
        verify(kafkaProducerService, times(1)).sendAppointmentScheduledEvent(appointment);

        assertEquals("Appointment Scheduled", response);
    }
}
