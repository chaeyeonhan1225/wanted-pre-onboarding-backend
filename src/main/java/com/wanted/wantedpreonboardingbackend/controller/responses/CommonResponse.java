package com.wanted.wantedpreonboardingbackend.controller.responses;

import com.wanted.wantedpreonboardingbackend.domain.exceptions.DomainException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommonResponse<T>  {
    private T data;
    private String message;
    private List<ExceptionResponse> errors;

    public static <T> CommonResponse<T> of(T data) {
        return new CommonResponse<>(data, null, Collections.emptyList());
    }

    public static <T> CommonResponse<T> of(T data, String message) {
        return new CommonResponse<>(data, message, Collections.emptyList());
    }

    public static <T> CommonResponse<T> error(Exception exception) {
        return new CommonResponse(null, null, ExceptionResponse.ofList(exception));
    }

    public static <T> CommonResponse<T> error(DomainException exception) {
        return new CommonResponse(null, null, ExceptionResponse.ofList(exception));
    }
}
