/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.patient.information.patientinformation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patient.information.patientinformation.entities.PatientInformation;

/**
 *
 * @author user
 */
@Repository
public interface PatientInformationRepository extends JpaRepository<PatientInformation, Integer> {

    Optional<PatientInformation> findByPatientId(long patientId);

    @Query(value = "select * from patient_information where age >= :start and age <= :end", nativeQuery = true)
    List<PatientInformation> findByAgeBetween(@Param("start") int startAge, @Param("end") int endAge);

    Optional<PatientInformation> findByEmail(String email);

}
