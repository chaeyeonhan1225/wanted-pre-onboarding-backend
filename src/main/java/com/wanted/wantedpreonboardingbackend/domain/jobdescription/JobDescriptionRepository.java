package com.wanted.wantedpreonboardingbackend.domain.jobdescription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
    List<JobDescription> findByCompanyId(Long companyId);
}
