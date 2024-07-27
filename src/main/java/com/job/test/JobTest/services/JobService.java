package com.job.test.JobTest.services;

import com.job.test.JobTest.dto.JobDTO;
import com.job.test.JobTest.util.JobResponse;
import com.job.test.JobTest.repositories.JobRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Value("${data-url}")
    private String apiUrl;

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @PostConstruct
    public void init() {
        loadJobs();
    }

    @Scheduled(fixedRate = 3600000)
    public void loadJobs() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 1; i <= 5; i++) {
            String paginatedUrl = apiUrl + "?page=" + i;
            JobResponse response = restTemplate.getForObject(paginatedUrl, JobResponse.class);
            if (response != null && response.getData() != null) {
                jobRepository.saveAll(response.getData());
            }
        }
    }

    public Page<JobDTO> getJobs(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    public List<JobDTO> getTop10Jobs() {
        Pageable topTen = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        return jobRepository.findAll(topTen).getContent();
    }

    public Map<String, Long> getJobStatistics() {
        return jobRepository.findAll().stream()
                .collect(Collectors.groupingBy(JobDTO::getLocation, Collectors.counting()));
    }
}
