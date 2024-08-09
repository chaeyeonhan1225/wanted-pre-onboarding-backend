package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.exceptions.JobDescriptionNotFoundException;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionFilter;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionRepository;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionSpec;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionDetail;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionSimple;
import org.springframework.data.jpa.domain.Specification;
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

    public List<JobDescriptionSimple> findAll(JobDescriptionFilter filter) {
        List<JobDescription> jds;
        if (filter.search() != null) {
            Specification<JobDescription> spec = new JobDescriptionSpec(filter).build();
            jds = repository.findAll(spec);
        } else {
            jds = repository.findAll();
        }

        return jds.stream().map(JobDescriptionSimple::new).toList();
    }

    public JobDescriptionDetail findById(Long id) {
        JobDescription jd = repository.findById(id).orElseThrow(() -> new JobDescriptionNotFoundException(id));
        List<JobDescription> relatedJds = repository.findByCompanyId(jd.getCompany().getId());
        return new JobDescriptionDetail(jd, relatedJds);
    }
}
