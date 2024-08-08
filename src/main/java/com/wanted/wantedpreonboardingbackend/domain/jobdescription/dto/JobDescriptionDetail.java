package com.wanted.wantedpreonboardingbackend.domain.jobdescription.dto;

import com.wanted.wantedpreonboardingbackend.domain.company.Company;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;

import java.util.List;

public record JobDescriptionDetail(Long id,
                                   String position,
                                   String description,
                                   Long reward,
                                   List<JobDescriptionSimple> relatedJobDescriptions,
                                   Company company) {
    public JobDescriptionDetail {
    }

    public JobDescriptionDetail(JobDescription jd, List<JobDescription> relatedJobDescriptions) {
        this(
                jd.getId(),
                jd.getPosition(),
                jd.getDescription(),
                jd.getReward(),
                relatedJobDescriptions
                        .stream()
                        .map(j -> new JobDescriptionSimple(j))
                        .toList(),
                jd.getCompany()
        );
    }
}
