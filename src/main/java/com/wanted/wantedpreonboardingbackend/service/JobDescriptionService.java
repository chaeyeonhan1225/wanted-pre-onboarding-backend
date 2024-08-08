package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.company.Company;
import com.wanted.wantedpreonboardingbackend.domain.company.CompanyRepository;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.CompanyNotFoundException;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.JobDescriptionNotFoundException;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionCreateParam;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionRepository;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionUpdateParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobDescriptionService {

    private JobDescriptionRepository repository;
    private CompanyRepository companyRepository;

    public JobDescriptionService(JobDescriptionRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public JobDescription create(JobDescriptionCreateParam param) {
        Company company = companyRepository.findById(param.companyId())
                .orElseThrow(() -> new CompanyNotFoundException(param.companyId()));
        JobDescription jd = new JobDescription(param, company);
        return repository.save(jd);
    }

    public JobDescription update(Long id, JobDescriptionUpdateParam param) {
        JobDescription jd = repository.findById(id)
                .orElseThrow(() -> new JobDescriptionNotFoundException(id));
        jd.update(param);
        jd.getCompany();
        return repository.save(jd);
    }

    public boolean delete(Long id) {
        JobDescription jd = repository.findById(id)
                .orElseThrow(() -> new JobDescriptionNotFoundException(id));
        jd.delete();
        repository.save(jd);
        return true;
    }
}
