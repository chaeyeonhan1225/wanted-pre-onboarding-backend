package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescription;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.JobDescriptionFilter;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionDetail;
import com.wanted.wantedpreonboardingbackend.service.dto.JobDescriptionSimple;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionCreateParam;
import com.wanted.wantedpreonboardingbackend.domain.jobdescription.param.JobDescriptionUpdateParam;
import com.wanted.wantedpreonboardingbackend.domain.user.UserApply;
import com.wanted.wantedpreonboardingbackend.domain.user.param.UserApplyParam;
import com.wanted.wantedpreonboardingbackend.service.JobDescriptionProvider;
import com.wanted.wantedpreonboardingbackend.service.JobDescriptionService;
import com.wanted.wantedpreonboardingbackend.service.UserApplyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<JobDescriptionSimple> findAll(@RequestParam(required = false) String search) {
        return provider.findAll(new JobDescriptionFilter(search));
    }

    @GetMapping("/{id}")
    public JobDescriptionDetail findById(@PathVariable Long id) {
        return provider.findById(id);
    }

    @PostMapping
    public JobDescription create(@RequestBody JobDescriptionCreateParam param) {
        return service.create(param);
    }

    @PatchMapping("/{id}")
    public JobDescription update(@PathVariable Long id, @RequestBody JobDescriptionUpdateParam param) {
        return service.update(id, param);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PostMapping("/{id}/apply")
    public UserApply apply(@PathVariable Long id, @RequestBody UserApplyParam param) {
        return applyService.apply(id, param);
    }
}
