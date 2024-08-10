package com.wanted.wantedpreonboardingbackend.controller.responses;

import com.wanted.wantedpreonboardingbackend.domain.exceptions.DomainException;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.ExceptionCode;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.InvalidInputException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    // FieldError는 따로 처리
    public static List<ExceptionResponse> ofList(InvalidInputException exception) {
        System.out.println("exception = " + exception.getFieldErrors());
        Map<String, String> fieldErrors = exception.getFieldErrors();
        String code = exception.getCode().getValue();
        return fieldErrors.entrySet().stream().map(
                e -> new ExceptionResponse(code, e.getValue(), e.getKey())
        ).toList();
    }


    public static List<ExceptionResponse> ofList(DomainException exception) {
        return Collections.singletonList(new ExceptionResponse(exception.getCode().getValue(), exception.getMessage()));
    }


    public static List<ExceptionResponse> ofList(RuntimeException exception) {
        return Collections.singletonList(new ExceptionResponse(ExceptionCode.INTERNAL_SERVER_ERROR.getValue(),
                exception.getMessage()));
    }

    public static List<ExceptionResponse> ofList(Exception exception) {
        return Collections.singletonList(new ExceptionResponse(ExceptionCode.INTERNAL_SERVER_ERROR.getValue(),
                "Internal Server Error"));
    }
}
