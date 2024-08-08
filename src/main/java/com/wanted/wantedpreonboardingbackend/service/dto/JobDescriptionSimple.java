package com.wanted.wantedpreonboardingbackend.service.dto;

import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;

public record JobDescriptionSimple(
        Long id,
        String position,
        String techSpec,
        Long reward,
        CompanySimple company
) {
    public JobDescriptionSimple {
    }

    public JobDescriptionSimple(JobDescription jd) {
        this(jd.getId(), jd.getPosition(), jd.getTechSpec(), jd.getReward(), new CompanySimple(jd.getCompany()));
    }
}
