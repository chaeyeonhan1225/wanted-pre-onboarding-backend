package com.wanted.wantedpreonboardingbackend.domain.exceptions;

public class JobDescriptionNotFoundException extends DomainException {
    public JobDescriptionNotFoundException(Long id) {
        super(ExceptionCode.NOT_FOUND, "존재하지 않는 채용공고(id=" + id + ") 입니다.");
    }
}
