/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.patient.information.patientinformation.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.patient.information.patientinformation.DTO.PatientInformationDTO;
import com.patient.information.patientinformation.DTO.ServiceResponse;
import com.patient.information.patientinformation.entities.PatientInformation;
import com.patient.information.patientinformation.exception.ResourceNotFoundException;
import com.patient.information.patientinformation.exception.ServiceException;
import com.patient.information.patientinformation.repository.PatientInformationRepository;
import com.patient.information.patientinformation.services.PatientInformationServices;
import com.patient.information.patientinformation.util.ApplicationUtil;

/**
 *
 * @author user
 */
@Service
public class PatientInformationServiceImplementation implements PatientInformationServices {

    @Autowired
    private PatientInformationRepository patientInformationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceResponse createPatientInformation(PatientInformationDTO dto) throws Exception {
	if (!ApplicationUtil.isEmailValid(dto.getEmail())) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST, "email address is invalid");
	}
	if (patientInformationRepository.findByEmail(dto.getEmail()).isPresent()) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST,
		    "patient with email " + dto.getEmail() + " already exists.");
	}
	PatientInformation info = modelMapper.map(dto, PatientInformation.class);
	info.setRegisteredDate(new Date());
	info.setAge(Integer.valueOf(dto.getAge()));
	info.setPatientId(Long.valueOf(ApplicationUtil.generateUniquePatientId()));
	return ServiceResponse.builder().message("Patient saved successfully").status(HttpStatus.OK)
		.data(patientInformationRepository.save(info)).build();


    }

    @Override
    public ServiceResponse findByPatientId(long patientId) throws Exception {
	return patientInformationRepository.findByPatientId(patientId).map(patient -> {
	    return ServiceResponse.builder().message("Patient found successfully").status(HttpStatus.OK).data(patient)
		    .build();
	}).orElseThrow(
		() -> new ResourceNotFoundException("The patient with patient id " + patientId + " does not exist"));
    }

    @Override
    public ServiceResponse findById(int id) throws Exception {
	return patientInformationRepository.findById(id).map(patient -> {
	    return ServiceResponse.builder().message("Patient found successfully").status(HttpStatus.OK).data(patient)
		    .build();
	}).orElseThrow(() -> new ResourceNotFoundException("The patient with id " + id + " does not exist"));
    }

    @Override
    public ServiceResponse findAll() throws Exception {
	List<PatientInformation> patients = patientInformationRepository.findAll();
	String message = !patients.isEmpty() ? "Patients found successfully" : "No records found";
	return ServiceResponse.builder().message(message).status(HttpStatus.OK).data(patients).build();
    }

    @Override
    public ServiceResponse findByAgeBetween(int startAge, int endAge) throws Exception {
	if (startAge < 0 || endAge < 0 || startAge > endAge) {
	    throw new ServiceException(HttpStatus.BAD_REQUEST, "invalid age range provided");
	}
	List<PatientInformation> patients = patientInformationRepository.findByAgeBetween(startAge, endAge);
	String message = !patients.isEmpty() ? "Patients found successfully" : "No records found for this age range";
	return ServiceResponse.builder().message(message).status(HttpStatus.OK).data(patients).build();
    }

}
