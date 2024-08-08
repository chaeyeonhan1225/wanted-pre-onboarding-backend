package com.wanted.wantedpreonboardingbackend.domain.jobdescription.param;

public record JobDescriptionCreateParam(String position, Long reward, String description, String techSpec,
                                        Long companyId) {
}
