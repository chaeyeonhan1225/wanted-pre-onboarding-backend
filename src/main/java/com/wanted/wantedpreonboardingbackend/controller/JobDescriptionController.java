package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.controller.responses.CommonResponse;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionFilter;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionCreateParam;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionUpdateParam;
import com.wanted.wantedpreonboardingbackend.domain.user.param.UserApplyParam;
import com.wanted.wantedpreonboardingbackend.service.JobDescriptionProvider;
import com.wanted.wantedpreonboardingbackend.service.JobDescriptionService;
import com.wanted.wantedpreonboardingbackend.service.UserApplyService;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionSimple;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "jobdescriptions", description = "JobDescription API")
@RequestMapping("/jobdescriptions")
public class JobDescriptionController {
    private JobDescriptionService service;
    private JobDescriptionProvider provider;
    private UserApplyService applyService;

    public JobDescriptionController(JobDescriptionService service,
                                    JobDescriptionProvider provider,
                                    UserApplyService applyService) {
        this.service = service;
        this.provider = provider;
        this.applyService = applyService;
    }

    @Operation(summary = "Job Description 조회", description = "채용공고 조회")
    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String search) {
        List<JobDescriptionSimple> results = provider.findAll(new JobDescriptionFilter(search));
        return ResponseEntity.ok(CommonResponse.of(results));
    }

    @Operation(summary = "Job Description 상세 조회", description = "채용공고 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(CommonResponse.of(provider.findById(id)));
    }

    @Operation(summary = "Job Description 생성", description = "채용공고 생성")
    @PostMapping
    public ResponseEntity create(@RequestBody JobDescriptionCreateParam param) {
        return ResponseEntity.status(201).body(CommonResponse.of(service.create(param)));
    }

    @Operation(summary = "Job Description 수정", description = "채용공고 수정")
    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody JobDescriptionUpdateParam param) {
        return ResponseEntity.ok(CommonResponse.of(service.update(id, param)));
    }

    @Operation(summary = "Job Description 삭제", description = "채용공고 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
         Long deletedJdId = service.delete(id);
         return ResponseEntity.ok(CommonResponse.of(true,
                 "채용공고(id=" + deletedJdId + ")가 삭제 되었습니다."));
    }

    @Operation(summary = "Job Description 지원", description = "채용공고 지원")
    @PostMapping("/{id}/apply")
    public ResponseEntity apply(@PathVariable Long id, @RequestBody UserApplyParam param) {
        return ResponseEntity.ok(CommonResponse.of(applyService.apply(id, param), null));
    }
}
