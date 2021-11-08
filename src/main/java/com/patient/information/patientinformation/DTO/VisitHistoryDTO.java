/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.patient.information.patientinformation.DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class VisitHistoryDTO {
    private String allergy;
    private String health_condition;
    private String medication;
    private String familyHistory;
    @NotNull(message = "patient id must be provided")
    private Long patientId;

}
