package com.wanted.wantedpreonboardingbackend.domain.company;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Embeddable
class Location implements Serializable {
    String country;
    String region;
}
