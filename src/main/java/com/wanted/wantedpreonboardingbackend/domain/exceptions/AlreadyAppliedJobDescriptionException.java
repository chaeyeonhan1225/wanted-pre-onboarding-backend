package com.wanted.wantedpreonboardingbackend.domain.exceptions;

import java.util.UUID;

public class AlreadyAppliedJobDescriptionException extends DomainException {
    public AlreadyAppliedJobDescriptionException(Long jobDescriptionId) {
        super(ExceptionCode.BAD_REQUEST, "이미 지원한 공고(id=" + jobDescriptionId + ")입니다.");
    }
}
