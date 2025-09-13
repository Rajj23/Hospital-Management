package com.hospital.Hospital_Managment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointment;
    private String reason;
    private DoctorResponseDto doctor;
}
