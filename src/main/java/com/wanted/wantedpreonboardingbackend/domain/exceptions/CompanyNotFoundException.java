package com.wanted.wantedpreonboardingbackend.domain.exceptions;

public class CompanyNotFoundException extends DomainException {
    public CompanyNotFoundException(Long id) {
        super("존재하지 않는 회사 (id=" + id + ")입니다.");
    }
}
