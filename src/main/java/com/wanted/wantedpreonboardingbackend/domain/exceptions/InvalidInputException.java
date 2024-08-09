package com.wanted.wantedpreonboardingbackend.domain.exceptions;

import com.wanted.wantedpreonboardingbackend.controller.responses.ExceptionResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InvalidInputException extends DomainException {
    private List<String> fieldErrors;

    private List<String> parseFieldErrors(ConstraintViolationException ce) {
        List<ConstraintViolation<?>> constraintViolations = ce.getConstraintViolations().stream().toList();
        return constraintViolations.stream().map(v ->
                StreamSupport.stream(v.getPropertyPath().spliterator(), false)
                .reduce((first, second) -> second)
                .get().toString()).toList();

    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public InvalidInputException(ConstraintViolationException ce) {
        super(ExceptionCode.BAD_REQUEST, "잘못된 입력값입니다.");
        this.fieldErrors = parseFieldErrors(ce);
    }
}
