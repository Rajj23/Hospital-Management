package com.hospital.Hospital_Managment.service;

import com.hospital.Hospital_Managment.dto.PatientResponseDto;
import com.hospital.Hospital_Managment.entity.Patient;
import com.hospital.Hospital_Managment.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PatientResponseDto getPatientById(Long patientId){

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with ID: "+patientId));

        return modelMapper.map(patient,PatientResponseDto.class);
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize){
        return patientRepository.findAllPatients(PageRequest.of(pageNumber,pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient,PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}
