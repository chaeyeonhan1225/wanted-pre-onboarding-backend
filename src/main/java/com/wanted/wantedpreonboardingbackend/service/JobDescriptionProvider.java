package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.exceptions.JobDescriptionNotFoundException;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionFilter;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionRepository;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionSpec;
import com.wanted.wantedpreonboardingbackend.service.dto.CommonPage;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionDetail;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public CommonPage<JobDescriptionSimple> findAll(JobDescriptionFilter filter, int page, int size) {
        Page<JobDescription> jds;
        if (filter.search() != null) {
            Specification<JobDescription> spec = new JobDescriptionSpec(filter).build();
            jds = repository.findAll(spec, PageRequest.of(page - 1, size,
                    Sort.by(Sort.Direction.DESC, "id")));
        } else {
            jds = repository.findAll(PageRequest.of(page - 1, size,
                    Sort.by(Sort.Direction.DESC, "id")));
        }
        return new CommonPage<>(jds.hasNext(), jds.hasPrevious(),
                jds.getTotalPages(), jds.getNumberOfElements(),
                page, jds.stream().map(JobDescriptionSimple::new).toList());
    }

    public JobDescriptionDetail findById(Long id) {
        JobDescription jd = repository.findById(id).orElseThrow(() -> new JobDescriptionNotFoundException(id));
        List<JobDescription> relatedJds = repository.findByCompanyId(jd.getCompany().getId());
        return new JobDescriptionDetail(jd, relatedJds);
    }
}
