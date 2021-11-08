package com.patient.information.patientinformation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.patient.information.patientinformation.DTO.ServiceResponse;

@ResponseBody
@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public ServiceResponse nullException(NullPointerException e) {
	return new ServiceResponse(HttpStatus.EXPECTATION_FAILED, e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ServiceResponse resourceNotFoundException(ResourceNotFoundException e) {
	return new ServiceResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ServiceResponse illegalArgumentException(IllegalArgumentException e) {
	return new ServiceResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ServiceResponse serviceException(ServiceException e) {
	return new ServiceResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceResponse generalException(Exception e) {
	return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
