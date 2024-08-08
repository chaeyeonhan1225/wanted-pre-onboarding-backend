package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionRepository;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.dto.JobDescriptionDetail;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.dto.JobDescriptionSimple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class JobDescriptionProvider {

    private JobDescriptionRepository repository;

    public JobDescriptionProvider(JobDescriptionRepository repository) {
        this.repository = repository;
    }

    public List<JobDescriptionSimple> findAll() {
        List<JobDescription> jds =  repository.findAll();
        return jds.stream().map(jd -> new JobDescriptionSimple(jd)).toList();
    }

    public JobDescriptionDetail findById(Long id) {
        JobDescription jd = repository.findById(id).orElseThrow(() -> new RuntimeException());
        List<JobDescription> relatedJds = repository.findByCompanyId(jd.getCompany().getId());
        return new JobDescriptionDetail(jd, relatedJds);
    }
}
