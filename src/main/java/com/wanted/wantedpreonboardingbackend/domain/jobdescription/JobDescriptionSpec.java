package com.wanted.wantedpreonboardingbackend.domain.jobdescription;


import com.wanted.wantedpreonboardingbackend.domain.common.CommonState;
import org.springframework.data.jpa.domain.Specification;

public class JobDescriptionSpec {
    private JobDescriptionFilter filter;

    public JobDescriptionSpec(JobDescriptionFilter filter) {
        this.filter = filter;
    }

    public static Specification<JobDescription> bySearchPositionLike(String searchKeyword) {
        return (root, query, builder) ->
                builder.like(root.get("position"), "%" + searchKeyword + "%");
    }

    public static Specification<JobDescription> bySearchTechSpecLike(String searchKeyword) {
        return (root, query, builder) ->
                builder.like(root.get("techSpec"), "%" + searchKeyword + "%");
    }

    public static Specification<JobDescription> byCompanyNameLike(String searchKeyword) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.join("company").get("name"), "%" + searchKeyword + "%");
        });
    }

    private Specification<JobDescription> base() {
        return (root, query, builder) ->
                builder.notEqual(root.get("status"), CommonState.DELETED);
    }

    public Specification<JobDescription> build() {
        Specification<JobDescription> spec = base();

        if (filter.search() != null) {
            Specification<JobDescription> bySerachPositionLike = JobDescriptionSpec.bySearchPositionLike(filter.search());
            Specification<JobDescription> bySearchTechSpecLike = JobDescriptionSpec.bySearchTechSpecLike(filter.search());
            Specification<JobDescription> byCompanyNameLike = JobDescriptionSpec.byCompanyNameLike(filter.search());

            spec = spec.and(bySerachPositionLike.or(bySearchTechSpecLike).or(byCompanyNameLike));
        }

        return spec;
    }

}
