package com.wanted.wantedpreonboardingbackend.domain.exceptions;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(Long id) {
        super("존재하지 않는 유저(id = " + id +") 입니다.");
    }
}
