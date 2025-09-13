package com.hospital.Hospital_Managment.repository;

import com.hospital.Hospital_Managment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
