package com.wanted.wantedpreonboardingbackend.domain.exceptions;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    ExceptionCode code;

    public DomainException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }
}
