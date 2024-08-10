package com.wanted.wantedpreonboardingbackend.domain.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.HashMap;
import java.util.Map;

public class InvalidInputException extends DomainException {
    Map<String, String> fieldErrors;

    public InvalidInputException(ConstraintViolationException ce) {
        super(ExceptionCode.INVALID_INPUT, "잘못된 입력값입니다.");
        this.fieldErrors = this.parseFieldErrors(ce);
    }

    private Map<String, String> parseFieldErrors(ConstraintViolationException ce) {
        Map<String, String> fieldsErrors = new HashMap<>();
        for (ConstraintViolation<?> constraintViolation : ce.getConstraintViolations()) {
            String field = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();

            fieldsErrors.put(field, message);
        }

        return fieldsErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
