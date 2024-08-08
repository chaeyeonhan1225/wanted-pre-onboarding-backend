package com.wanted.wantedpreonboardingbackend.domain.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wanted.wantedpreonboardingbackend.domain.common.CommonState;
import com.wanted.wantedpreonboardingbackend.domain.common.EntityBase;
import com.wanted.wantedpreonboardingbackend.domain.company.Location;
import com.wanted.wantedpreonboardingbackend.infrastructure.CommonStateConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Getter(AccessLevel.PUBLIC)
@Table(name="Company")
@Entity
@SQLRestriction("status > 0")
public class Company extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Embedded
    Location location;

    @JsonIgnore
    @Column
    @Convert(converter = CommonStateConverter.class)
    private CommonState status = CommonState.ACTIVE;
}
