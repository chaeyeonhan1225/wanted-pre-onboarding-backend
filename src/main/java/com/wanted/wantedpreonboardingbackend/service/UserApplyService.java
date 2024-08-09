package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.exceptions.DomainException;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.JobDescriptionNotFoundException;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.UserNotFoundException;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionRepository;
import com.wanted.wantedpreonboardingbackend.domain.user.User;
import com.wanted.wantedpreonboardingbackend.domain.user.UserApply;
import com.wanted.wantedpreonboardingbackend.domain.user.UserRepository;
import com.wanted.wantedpreonboardingbackend.domain.user.param.UserApplyParam;
import com.wanted.wantedpreonboardingbackend.service.dto.UserApplySimple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserApplyService {
    private UserRepository userRepository;
    private JobDescriptionRepository jobDescriptionRepository;

    public UserApplyService(UserRepository userRepository, JobDescriptionRepository jobDescriptionRepository) {
        this.userRepository = userRepository;
        this.jobDescriptionRepository = jobDescriptionRepository;
    }

    public UserApplySimple apply(Long jobDescriptionId, UserApplyParam param) {
        User user = userRepository.findById(param.userId())
                .orElseThrow(() -> new UserNotFoundException(param.userId()));
        JobDescription jd = jobDescriptionRepository.findById(jobDescriptionId)
                .orElseThrow(() -> new JobDescriptionNotFoundException(jobDescriptionId));
        userRepository.save(user);
        return new UserApplySimple(user.applyJob(jd));
    }

}
