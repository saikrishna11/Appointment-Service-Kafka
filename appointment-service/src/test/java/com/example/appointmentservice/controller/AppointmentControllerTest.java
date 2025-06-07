package com.example.appointmentservice.controller;

import com.example.appointmentservice.kafka.KafkaProducerService;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private KafkaProducerService kafkaProducerService; // Mock KafkaProducerService

    @InjectMocks
    private AppointmentController appointmentController;

    private MockMvc mockMvc;

    @Test
    void testScheduleAppointment() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();

        String patientId = "123";
        String doctor = "Dr. Smith";
        String appointmentDate = "2025-07-10";
        String appointmentTime = "10:00";

        Appointment appointment = new Appointment(patientId, doctor, appointmentDate, appointmentTime);

        when(appointmentService.scheduleAppointment(appointment)).thenReturn("Appointment Scheduled");

        String appointmentJson = String.format(
                "{\"patientId\":\"%s\", \"doctor\":\"%s\", \"date\":\"%s\", \"time\":\"%s\"}",
                patientId, doctor, appointmentDate, appointmentTime
        );

        mockMvc.perform(post("/appointments")
                        .contentType("application/json")
                        .content(appointmentJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Appointment Scheduled"));
    }
}