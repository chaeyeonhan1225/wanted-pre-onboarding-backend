package com.wanted.wantedpreonboardingbackend.domain.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wanted.wantedpreonboardingbackend.domain.common.CommonState;
import com.wanted.wantedpreonboardingbackend.domain.common.EntityBase;
import com.wanted.wantedpreonboardingbackend.infrastructure.CommonStateConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
