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

    // TODO: CompanyNameLike도 구현해야함
    public static Specification<JobDescription> bySearchTechSpecLike(String searchKeyword) {
        return (root, query, builder) ->
                builder.like(root.get("techSpec"), "%" + searchKeyword + "%");
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

            spec = spec.and(bySerachPositionLike.or(bySearchTechSpecLike));
        }

        return spec;
    }

}
