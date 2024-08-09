package com.wanted.wantedpreonboardingbackend.domain.exceptions;

import java.util.UUID;

public class AlreadyAppliedJobDescriptionException extends DomainException {
    public AlreadyAppliedJobDescriptionException(UUID id) {
        super(ExceptionCode.BAD_REQUEST, "이미 지원한 공고(id = " + id + ") 입니다.");
    }
}
