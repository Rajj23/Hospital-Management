package com.hospital.Hospital_Managment.repository;

import com.hospital.Hospital_Managment.dto.BloodGroupCountResponseEntity;
import com.hospital.Hospital_Managment.entity.Patient;
import com.hospital.Hospital_Managment.entity.type.BloodGroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByName(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate,String email);
    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloopGroup")BloodGroupType bloodGroupType);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("select new com.hospital.Hospital_Managment.dto.BloodGroupCountResponseEntity(p.bloodGroup," +
            " Count(p)) from Patient p group by p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);


    @Query("update  Patient p set p.name = :name where p.id = :id")
    int updateNameWithID(@Param("name") String name, @Param("id") Long id);

    @Query("select p from Patient p left join FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();


}
