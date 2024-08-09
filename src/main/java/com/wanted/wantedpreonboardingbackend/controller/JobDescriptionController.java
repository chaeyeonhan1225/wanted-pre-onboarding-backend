package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionFilter;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionCreateParam;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionUpdateParam;
import com.wanted.wantedpreonboardingbackend.domain.user.UserApply;
import com.wanted.wantedpreonboardingbackend.domain.user.param.UserApplyParam;
import com.wanted.wantedpreonboardingbackend.service.JobDescriptionProvider;
import com.wanted.wantedpreonboardingbackend.service.JobDescriptionService;
import com.wanted.wantedpreonboardingbackend.service.UserApplyService;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionDetail;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionSimple;
import com.wanted.wantedpreonboardingbackend.service.dto.UserApplySimple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String search) {
        List<JobDescriptionSimple> results = provider.findAll(new JobDescriptionFilter(search));
        return ResponseEntity.ok(CommonResponse.of(results));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(CommonResponse.of(provider.findById(id)));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody JobDescriptionCreateParam param) {
        return ResponseEntity.status(201).body(CommonResponse.of(service.create(param)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody JobDescriptionUpdateParam param) {
        return ResponseEntity.ok(CommonResponse.of(service.update(id, param)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
         Long deletedJdId = service.delete(id);
         return ResponseEntity.ok(CommonResponse.of(true,
                 "채용공고(id=" + deletedJdId + ")가 삭제 되었습니다."));
    }

    @PostMapping("/{id}/apply")
    public ResponseEntity apply(@PathVariable Long id, @RequestBody UserApplyParam param) {
        return ResponseEntity.ok(CommonResponse.of(applyService.apply(id, param), null));
    }
}
