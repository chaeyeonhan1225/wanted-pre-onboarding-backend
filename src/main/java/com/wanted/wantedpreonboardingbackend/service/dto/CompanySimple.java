package com.wanted.wantedpreonboardingbackend.service.dto;

import com.wanted.wantedpreonboardingbackend.domain.company.Company;
import com.wanted.wantedpreonboardingbackend.domain.company.Location;

public record CompanySimple(
        Long id,
        String name,
        Location location
) {
    public CompanySimple(Company company) {
        this(company.getId(), company.getName(), company.getLocation());
    }
}
