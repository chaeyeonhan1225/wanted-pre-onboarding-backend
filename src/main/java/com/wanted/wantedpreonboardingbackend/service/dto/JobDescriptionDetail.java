package com.wanted.wantedpreonboardingbackend.service.dto;

import com.wanted.wantedpreonboardingbackend.domain.company.Company;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;

import java.util.List;

public record JobDescriptionDetail(Long id,
                                   String position,
                                   String description,
                                   Long reward,
                                   List<JobDescriptionSimple> relatedJobDescriptions,
                                   CompanySimple company) {
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
                new CompanySimple(jd.getCompany())
        );
    }
}
