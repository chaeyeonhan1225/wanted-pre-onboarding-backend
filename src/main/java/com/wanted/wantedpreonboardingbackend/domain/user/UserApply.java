package com.wanted.wantedpreonboardingbackend.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wanted.wantedpreonboardingbackend.domain.common.EntityBase;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Table(name = "`UserApply`",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "userJobDescriptionUnique",
                columnNames = {"userId", "jobDescriptionId"}
        )})
@Entity
public class UserApply extends EntityBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID uuid;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne(targetEntity = JobDescription.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "jobDescriptionId")
    JobDescription jobDescription;

    public UserApply(User user, JobDescription jobDescription) {
        this.user = user;
        this.jobDescription = jobDescription;
    }
}
