package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.domain.exceptions.DomainException;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.ExceptionCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    protected ResponseEntity<CommonResponse> handleDomainException(DomainException exception) {
        return ResponseEntity.status(exception.getCode().getValue())
                .body(CommonResponse.error(exception));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<CommonResponse> handleFieldException(ConstraintViolationException exception) {
        return ResponseEntity.status(ExceptionCode.BAD_REQUEST.getValue())
                .body(CommonResponse.error(exception));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<CommonResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(ExceptionCode.BAD_REQUEST.getValue())
                .body(CommonResponse.error(exception));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<CommonResponse> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.status(ExceptionCode.INTERNAL_SERVER_ERROR.getValue())
                .body(CommonResponse.error(exception));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse> handleException(Exception exception) {
        return ResponseEntity.status(ExceptionCode.INTERNAL_SERVER_ERROR.getValue())
                .body(CommonResponse.error(exception));
    }

}
