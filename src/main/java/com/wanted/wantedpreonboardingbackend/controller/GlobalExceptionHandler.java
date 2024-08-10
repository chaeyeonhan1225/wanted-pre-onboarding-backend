package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.controller.responses.CommonResponse;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.DomainException;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.ExceptionCode;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.InvalidInputException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    protected ResponseEntity<CommonResponse> handleDomainException(DomainException exception) {
        return ResponseEntity.status(exception.getCode().getStatusCode())
                .body(CommonResponse.error(exception));
    }

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<CommonResponse> handleFieldException(InvalidInputException exception) {
        return ResponseEntity.status(ExceptionCode.BAD_REQUEST.getStatusCode())
                .body(CommonResponse.error(exception));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<CommonResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(ExceptionCode.BAD_REQUEST.getStatusCode())
                .body(CommonResponse.error(exception));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<CommonResponse> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.status(ExceptionCode.INTERNAL_SERVER_ERROR.getStatusCode())
                .body(CommonResponse.error(exception));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<CommonResponse> handleMethodNotAllowedException(HttpClientErrorException.MethodNotAllowed exception) {
        return ResponseEntity.status(ExceptionCode.BAD_REQUEST.getStatusCode())
                .body(CommonResponse.error(exception));
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse> handleException(Exception exception) {
        System.out.println("exception = " + exception);
        return ResponseEntity.status(ExceptionCode.INTERNAL_SERVER_ERROR.getStatusCode())
                .body(CommonResponse.error(exception));
    }

}
