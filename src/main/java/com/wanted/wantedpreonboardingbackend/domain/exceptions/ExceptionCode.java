package com.wanted.wantedpreonboardingbackend.domain.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    BAD_REQUEST(400, "D001"),
    INVALID_INPUT(400, "D002"),
    ALREADY_APPLIED(400, "D003"),
    NOT_FOUND(404, "D004"),
    INTERNAL_SERVER_ERROR(500, "A001");

    private int statusCode;
    private String value;

    ExceptionCode(int statusCode, String value) {
        this.statusCode = statusCode;
        this.value = value;
    }
}
