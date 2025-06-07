package com.example.appointmentservice.kafka;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaConsumerServiceTest {

    private KafkaConsumerService kafkaConsumerService;
    private TestAppender testAppender;

    @BeforeEach
    void setUp() {
        kafkaConsumerService = new KafkaConsumerService();

        // Get the logger instance for KafkaConsumerService
        Logger logger = (Logger) LoggerFactory.getLogger(KafkaConsumerService.class);

        // Create and attach a custom appender to capture log messages
        testAppender = new TestAppender();
        logger.addAppender(testAppender);
        testAppender.start();
    }

    @Test
    void testListenAppointmentScheduled() {
        String message = "Appointment Scheduled: PatientId: 123, Doctor: Dr. Smith, Time: 2025-07-10T10:00";

        // Simulate the Kafka message consumption
        kafkaConsumerService.listenAppointmentScheduled(message);

        // Verify the log message
        assertThat(testAppender.getLoggedMessages())
                .containsExactly("Received Appointment Scheduled Event: " + message);
    }

    // Custom appender to capture log messages
    static class TestAppender extends AppenderBase<ILoggingEvent> {
        private final List<String> loggedMessages = new ArrayList<>();

        @Override
        protected void append(ILoggingEvent eventObject) {
            loggedMessages.add(eventObject.getFormattedMessage());
        }

        public List<String> getLoggedMessages() {
            return loggedMessages;
        }
    }
}