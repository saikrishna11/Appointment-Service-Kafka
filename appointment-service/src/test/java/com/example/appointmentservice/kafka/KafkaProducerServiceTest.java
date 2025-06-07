package com.example.appointmentservice.kafka;

import com.example.appointmentservice.model.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    @Test
    void testSendAppointmentScheduledEvent() {
        Appointment appointment = new Appointment("123", "Dr. Smith", "2025-07-10", "10:00");
        kafkaProducerService.sendAppointmentScheduledEvent(appointment);

        // Verify the exact message being sent
        String expectedMessage = appointment.toString(); // Use the toString() method
        verify(kafkaTemplate, times(1)).send("appointment-scheduled", expectedMessage);
    }
}
