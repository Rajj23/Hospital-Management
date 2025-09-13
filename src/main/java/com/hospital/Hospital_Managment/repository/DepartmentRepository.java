package com.hospital.Hospital_Managment.repository;

import com.hospital.Hospital_Managment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
