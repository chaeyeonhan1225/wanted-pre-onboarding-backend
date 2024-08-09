package com.wanted.wantedpreonboardingbackend.domain.jobdescription;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wanted.wantedpreonboardingbackend.domain.common.CommonState;
import com.wanted.wantedpreonboardingbackend.domain.common.EntityBase;
import com.wanted.wantedpreonboardingbackend.domain.company.Company;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionCreateParam;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionUpdateParam;
import com.wanted.wantedpreonboardingbackend.infrastructure.CommonStateConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="JobDescription")
@Entity
@SQLRestriction("status > 0")
public class JobDescription extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "포지션은 필수입니다.")
    @Column(length = 255, nullable = false)
    String position;

    @PositiveOrZero(message = "보상금은 0원 이상이어야 합니다.")
    @Column(nullable = false)
    Long reward;

    @NotBlank(message = "상세 설명은 필수입니다.")
    @Column(nullable = false)
    String description;

    @NotBlank(message = "사용 기술은 필수입니다.")
    @Column(nullable = false)
    String techSpec;

    @ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="companyId")
    Company company;

    @JsonIgnore
    @Column(nullable = false)
    @Convert(converter = CommonStateConverter.class)
    private CommonState status = CommonState.ACTIVE;

    public JobDescription(JobDescriptionCreateParam param, Company company) {
        position = param.position();
        reward = param.reward();
        description = param.description();
        techSpec = param.techSpec();
        this.company = company;
    }

    public void update(JobDescriptionUpdateParam param) {
        position = param.position();
        reward = param.reward();
        description = param.description();
        techSpec = param.techSpec();
    }

    public void delete() {
        status = CommonState.DELETED;
    }
}
