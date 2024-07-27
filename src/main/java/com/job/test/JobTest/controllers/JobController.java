package com.job.test.JobTest.controllers;

import com.job.test.JobTest.dto.JobDTO;
import com.job.test.JobTest.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public Page<JobDTO> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return jobService.getJobs(pageable);
    }

    @GetMapping("/top10")
    public List<JobDTO> getTop10Jobs() {
        return jobService.getTop10Jobs();
    }

    @GetMapping("/statistics")
    public Map<String, Long> getJobStatistics() {
        return jobService.getJobStatistics();
    }
}
