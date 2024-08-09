package com.wanted.wantedpreonboardingbackend.domain.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    BAD_REQUEST(400, "D001"),
    ALREADY_APPLIED(400, "D002"),
    NOT_FOUND(404, "D003"),
    INTERNAL_SERVER_ERROR(500, "A001");

    private int value;
    private String code;

    ExceptionCode(int value, String code) {
        this.value = value;
        this.code = code;
    }
}
