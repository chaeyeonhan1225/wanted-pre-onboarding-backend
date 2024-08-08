package com.wanted.wantedpreonboardingbackend.domain.exceptions;

public class JobDescriptionNotFoundException extends DomainException {
    public JobDescriptionNotFoundException(Long id) {
        super("존재하지 않는 채용공고 (id=" + id + ") 입니다.");
    }
}
