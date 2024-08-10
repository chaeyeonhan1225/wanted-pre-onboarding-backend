package com.wanted.wantedpreonboardingbackend.domain.jobdescription;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long>, JpaSpecificationExecutor<JobDescription> {
    Page<JobDescription> findByCompanyId(Long companyId, Pageable pageable);

    @EntityGraph(attributePaths = "company")
    List<JobDescription> findAll();

    @EntityGraph(attributePaths = "company")
    List<JobDescription> findAll(Specification<JobDescription> specification);
}
