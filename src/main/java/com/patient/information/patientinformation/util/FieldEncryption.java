package com.patient.information.patientinformation.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FieldEncryption {
    public String encode(String value) {
        try {
            return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            //   e.printStackTrace();
            return null;
        }

    }
}
