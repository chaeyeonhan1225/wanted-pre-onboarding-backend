package com.wanted.wantedpreonboardingbackend.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wanted.wantedpreonboardingbackend.domain.common.EntityBase;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="`User`")
@Entity
public class User extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 512, unique = true)
    String email = "";

    @Column(length = 20)
    String nickname = "";

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    List<UserApply> applies = new ArrayList<>();

    public UserApply applyJob(JobDescription jobDescription) {
        UserApply apply = new UserApply(this, jobDescription);
        applies.add(apply);
        return apply;
    }
}
