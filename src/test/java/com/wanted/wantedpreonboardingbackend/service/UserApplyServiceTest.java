package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.company.Company;
import com.wanted.wantedpreonboardingbackend.domain.company.CompanyRepository;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.AlreadyAppliedJobDescriptionException;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionRepository;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionCreateParam;
import com.wanted.wantedpreonboardingbackend.domain.user.User;
import com.wanted.wantedpreonboardingbackend.domain.user.UserRepository;
import com.wanted.wantedpreonboardingbackend.domain.user.param.UserApplyParam;
import com.wanted.wantedpreonboardingbackend.service.dto.UserApplySimple;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserApplyServiceTest {

    @Autowired
    private UserApplyService service;

    @Autowired
    private JobDescriptionRepository jobDescriptionRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Test
    public void testApply() {
        // given
        Company company = this.companyRepository.findById(1L).get();
        JobDescriptionCreateParam param = new JobDescriptionCreateParam(
                "Backend Developer",
                1000000L,
                "스프링 개발자 3년차 이상",
                "Java, Spring",
                1L
        );
        JobDescription jd = new JobDescription(param, company);
        jobDescriptionRepository.save(jd);

        // when
        UserApplyParam userApplyParam = new UserApplyParam(1L);
        UserApplySimple applyResult = service.apply(jd.getId(), userApplyParam);

        // then
        Assertions.assertThat(applyResult.userId()).isEqualTo(userApplyParam.userId());
        Assertions.assertThat(applyResult.jobDescriptionId()).isEqualTo(jd.getId());
    }

    @Test
    public void testDuplicateApply() {
        // given
        Company company = this.companyRepository.findById(1L).get();
        JobDescriptionCreateParam param = new JobDescriptionCreateParam(
                "Backend Developer",
                1000000L,
                "스프링 개발자 3년차 이상",
                "Java, Spring",
                1L
        );
        JobDescription jd = new JobDescription(param, company);
        jobDescriptionRepository.save(jd);

        // when
        UserApplyParam userApplyParam = new UserApplyParam(1L);
        service.apply(jd.getId(), userApplyParam);

        // then
        // 중복지원
        assertThrows(AlreadyAppliedJobDescriptionException.class,
                () -> service.apply(jd.getId(), userApplyParam));
    }

}