/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.patient.information.patientinformation.DTO;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInformationDTO {
    private String firstName;
    private String lastName;
    @NotBlank(message = "address must be provided")
    @NotNull(message = "address must be provided")
    private String address;
    @NotBlank(message = "date of birth must be provided")
    @NotNull(message = "date of birth must be provided")
    private String dateOfBirth;
    @NotNull(message = "age must be provided")
    private String age;
    private String phoneNumber;
    private String email;
    private String gender;
    private String occupation;
    private String maritalStatus;

}
