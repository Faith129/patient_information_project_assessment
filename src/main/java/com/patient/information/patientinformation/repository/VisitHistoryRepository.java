/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.patient.information.patientinformation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patient.information.patientinformation.entities.PatientInformation;
import com.patient.information.patientinformation.entities.VisitHistory;

@Repository
public interface VisitHistoryRepository extends JpaRepository<VisitHistory, Integer> {

    List<VisitHistory> findByPatientInfo(long patientInfo);

}
