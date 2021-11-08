/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.patient.information.patientinformation.services.impl;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.information.patientinformation.services.VisitHistoryService;
import com.patient.information.patientinformation.util.FieldEncryption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.patient.information.patientinformation.DTO.ServiceResponse;
import com.patient.information.patientinformation.DTO.VisitHistoryDTO;
import com.patient.information.patientinformation.entities.PatientInformation;
import com.patient.information.patientinformation.entities.VisitHistory;
import com.patient.information.patientinformation.exception.ResourceNotFoundException;
import com.patient.information.patientinformation.repository.PatientInformationRepository;
import com.patient.information.patientinformation.repository.VisitHistoryRepository;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author user
 */
@Service
public class VisitHistoryServiceImplementation implements VisitHistoryService {
    @Autowired
    private VisitHistoryRepository visitHistoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PatientInformationRepository patientInformationRepository;

    @Override
    public ServiceResponse findAll() throws Exception {
	List<VisitHistory> history = visitHistoryRepository.findAll();
	String message = !history.isEmpty() ? "History found successfully" : "No records found";
	return ServiceResponse.builder().message(message).status(HttpStatus.OK).data(history).build();
    }

    @Override
    public ServiceResponse createVisit(VisitHistoryDTO dto) throws Exception {
	PatientInformation patientInfo = patientInformationRepository.findByPatientId(dto.getPatientId()).orElseThrow(
		() -> new ResourceNotFoundException("The patient with id " + dto.getPatientId() + " does not exist"));
	VisitHistory history = modelMapper.map(dto, VisitHistory.class);
	history.setPatientInfo(dto.getPatientId());
	history.setDateVisited(new Date());
	return ServiceResponse.builder().message("History saved successfully").status(HttpStatus.OK)
		.data(visitHistoryRepository.save(history)).build();
    }

    @Override
    public ServiceResponse findHistoryByPatientId(long patientId) throws Exception {
	PatientInformation patientInfo = patientInformationRepository.findByPatientId(patientId).orElseThrow(
		() -> new ResourceNotFoundException("The patient with id " + patientId + " does not exist"));
	List<VisitHistory> history = visitHistoryRepository.findByPatientInfo(patientId);
	String message = !history.isEmpty() ? "History found successfully" : "No records found";
	return ServiceResponse.builder().message(message).status(HttpStatus.OK).data(history).build();
    }

}
