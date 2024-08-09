package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.common.CommonState;
import com.wanted.wantedpreonboardingbackend.domain.exceptions.JobDescriptionNotFoundException;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionFilter;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionRepository;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionUpdateParam;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionSimple;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionCreateParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class JobDescriptionServiceTest {
    @Autowired
    private JobDescriptionService service;

    @Autowired
    private JobDescriptionProvider provider;

    @Autowired
    private JobDescriptionRepository repository;

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

        JobDescriptionSimple jobDescription = service.create(param);
        List<JobDescriptionSimple> results = provider.findAll(new JobDescriptionFilter(null));
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    public void jobDescriptionUpdateTest() {
        // given
        JobDescriptionCreateParam param = new JobDescriptionCreateParam(
                "Backend Developer",
                100000L,
                "백엔드 개발자 1분 모집",
                "Python",
                1L);

        JobDescriptionSimple jd = service.create(param);
        assertThat(jd.position()).isEqualTo(param.position());

        // when
        JobDescriptionUpdateParam updateParam = new JobDescriptionUpdateParam(
                "Backend Developer",
                100000L,
                "백엔드 개발자 1분 모집",
                "Spring"    // 수정
                );

        JobDescriptionSimple updatedJd = service.update(jd.id(), updateParam);

        // then
        assertThat(updatedJd.techSpec()).isEqualTo(updateParam.techSpec());
        assertThat(updatedJd.techSpec()).isNotEqualTo(param.techSpec());
    }

    @Test
    public void jobDescriptionUpdateFailTest() {
        // given
        // when
        JobDescriptionUpdateParam updateParam = new JobDescriptionUpdateParam(
                "Backend Developer",
                100000L,
                "백엔드 개발자 1분 모집",
                "Spring"    // 수정
        );

        // then
        // 존재하지 않는 id
        Assertions.assertThrows(JobDescriptionNotFoundException.class,() -> service.update(1231231231L, updateParam));
    }


    @Test
    public void jobDescriptionDeleteTest() {
        // given
        JobDescriptionCreateParam param = new JobDescriptionCreateParam(
                "Backend Developer",
                100000L,
                "백엔드 개발자 1분 모집",
                "Python",
                1L);

        JobDescriptionSimple jd = service.create(param);
        assertThat(jd.position()).isEqualTo(param.position());

        // when
        service.delete(jd.id());

        // then
        assertThat(repository.findById(jd.id()).isEmpty()).isTrue();
    }


    @Test
    public void jobDescriptionListTest() {
        // given
        JobDescriptionCreateParam param = new JobDescriptionCreateParam(
                "Backend Developer",
                100000L,
                "백엔드 개발자 1분 모집",
                "Python",
                1L);

        JobDescriptionSimple jd = service.create(param);
        assertThat(jd.position()).isEqualTo(param.position());

        // when
        JobDescriptionUpdateParam updateParam = new JobDescriptionUpdateParam(
                "Backend Developer",
                100000L,
                "백엔드 개발자 1분 모집",
                "Spring"    // 수정
        );

        JobDescriptionSimple updatedJd = service.update(jd.id(), updateParam);

        // then
        assertThat(updatedJd.techSpec()).isEqualTo(updateParam.techSpec());
        assertThat(updatedJd.techSpec()).isNotEqualTo(param.techSpec());
    }
}
