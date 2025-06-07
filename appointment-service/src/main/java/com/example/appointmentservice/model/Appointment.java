package com.example.appointmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private String patientId;
    private String doctor;
    private String date;
    private String time;

    @Override
    public String toString() {
        return "Appointment{" +
                "patientId='" + patientId + '\'' +
                ", doctor='" + doctor + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
