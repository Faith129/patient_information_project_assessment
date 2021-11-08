package com.patient.information.patientinformation.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationUtil {

    public static final String API_VERSION = "/api/v1/";
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "1234567890987654321";
    private static final int PATIENT_ID_LIMIT = 10;
    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
	    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isEmailValid(final String email) {
	Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	Matcher matcher = pattern.matcher(email);
	return matcher.matches();
    }

    public static String generateUniquePatientId() {
	StringBuilder main = new StringBuilder(PATIENT_ID_LIMIT);
	for (int i = 0; i < PATIENT_ID_LIMIT; i++) {
	    main.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	}
	return main.toString();
    }
}
