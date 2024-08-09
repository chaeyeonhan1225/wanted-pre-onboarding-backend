package com.wanted.wantedpreonboardingbackend.service.dto;

import com.wanted.wantedpreonboardingbackend.domain.user.UserApply;

import java.util.UUID;

public record UserApplySimple(
        UUID uuid,
        Long userId,
        Long jobDescriptionId
) {
    public UserApplySimple(UserApply apply) {
        this(apply.getUuid(), apply.getUser().getId(), apply.getJobDescription().getId());
    }
}
