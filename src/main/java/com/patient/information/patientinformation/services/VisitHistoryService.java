/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.patient.information.patientinformation.services;

import com.patient.information.patientinformation.DTO.ServiceResponse;
import com.patient.information.patientinformation.DTO.VisitHistoryDTO;

/**
 *
 * @author user
 */
public interface VisitHistoryService {
    ServiceResponse findHistoryByPatientId(long patientId) throws Exception;

    ServiceResponse findAll() throws Exception;

    ServiceResponse createVisit(VisitHistoryDTO dto) throws Exception;
}
