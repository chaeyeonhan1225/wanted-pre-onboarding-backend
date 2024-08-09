package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.domain.exceptions.DomainException;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.ExceptionCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionResponse {
    private String code;
    private String message;
    private String target;


    public ExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionResponse(String code, String message, String target) {
        this.code = code;
        this.message = message;
        this.target = target;
    }

    public static ExceptionResponse of(DomainException exception) {
        return new ExceptionResponse(exception.getCode().getCode(), exception.getMessage());
    }

    public static List<ExceptionResponse> ofList(DomainException exception) {
        return Collections.singletonList(new ExceptionResponse(exception.getCode().getCode(), exception.getMessage()));
    }

    public static ExceptionResponse of(ConstraintViolationException e) {
        return new ExceptionResponse(ExceptionCode.BAD_REQUEST.getCode(), e.getMessage());
    }

    public static List<ExceptionResponse> ofList(ConstraintViolationException exception) {

        List<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations().stream().toList();

        return constraintViolations.stream().map(v -> new ExceptionResponse(ExceptionCode.BAD_REQUEST.getCode(),
                v.getMessage(),
                StreamSupport.stream(v.getPropertyPath().spliterator(), false)
                        .reduce((first, second) -> second)
                        .get().toString())).toList();
    }

    public static List<ExceptionResponse> ofList(RuntimeException exception) {
        return Collections.singletonList(new ExceptionResponse(ExceptionCode.INTERNAL_SERVER_ERROR.getCode(),
                exception.getMessage()));
    }

    public static List<ExceptionResponse> ofList(Exception exception) {
        return Collections.singletonList(new ExceptionResponse(ExceptionCode.INTERNAL_SERVER_ERROR.getCode(),
                "Internal Server Error"));
    }
}
