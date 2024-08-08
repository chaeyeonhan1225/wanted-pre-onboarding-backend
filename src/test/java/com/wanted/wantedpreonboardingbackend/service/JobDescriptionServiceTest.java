package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.common.CommonState;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionSimple;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionCreateParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JobDescriptionServiceTest {
    @Autowired
    private JobDescriptionService service;

    @Autowired
    private JobDescriptionProvider provider;

    @Test
    public void enumTest() {
        assertThat(CommonState.ACTIVE.getValue()).isEqualTo(1);
        assertThat(CommonState.parse(1)).isEqualTo(CommonState.ACTIVE);

        assertThat(CommonState.DELETED.getValue()).isEqualTo(0);
        assertThat(CommonState.parse(0)).isEqualTo(CommonState.DELETED);
    }

    @Test
    public void jobDescriptionTest() {
        JobDescriptionCreateParam param = new JobDescriptionCreateParam("Backend Developer",
                100000L,
                "백엔드 개발자 1분 모집",
                "Python",
                1L);

        JobDescription jobDescription = service.create(param);
        assertThat(jobDescription.getStatus()).isEqualTo(CommonState.ACTIVE);
        assertThat(jobDescription.getStatus().getValue()).isEqualTo(CommonState.ACTIVE.getValue());
        assertThat(jobDescription.getStatus().getValue()).isEqualTo(1);
        List<JobDescriptionSimple> results = provider.findAll();
        assertThat(results.size()).isEqualTo(1);
    }
}