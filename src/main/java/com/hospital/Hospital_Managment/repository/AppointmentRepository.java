package com.hospital.Hospital_Managment.repository;

import com.hospital.Hospital_Managment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
