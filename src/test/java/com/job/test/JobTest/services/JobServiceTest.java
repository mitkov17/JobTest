package com.job.test.JobTest.services;

import com.job.test.JobTest.dto.JobDTO;
import com.job.test.JobTest.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class JobServiceTest {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobRepository jobRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetJobs() {
        JobDTO job = new JobDTO();
        job.setId(1L);
        job.setTitle("Software Engineer");

        Pageable pageable = PageRequest.of(0, 10);
        Page<JobDTO> jobPage = new PageImpl<>(Collections.singletonList(job), pageable, 1);

        when(jobRepository.findAll(pageable)).thenReturn(jobPage);

        Page<JobDTO> jobs = jobService.getJobs(pageable);

        assertNotNull(jobs);
        assertEquals(1, jobs.getTotalElements());
        assertEquals("Software Engineer", jobs.getContent().get(0).getTitle());
    }

    @Test
    public void testGetTop10Jobs() {
        JobDTO job = new JobDTO();
        job.setId(1L);
        job.setTitle("Software Engineer");

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<JobDTO> jobPage = new PageImpl<>(Collections.singletonList(job), pageable, 1);

        when(jobRepository.findAll(pageable)).thenReturn(jobPage);

        List<JobDTO> jobs = jobService.getTop10Jobs();

        assertNotNull(jobs);
        assertEquals(1, jobs.size());
        assertEquals("Software Engineer", jobs.get(0).getTitle());
    }

    @Test
    public void testGetJobStatistics() {
        JobDTO job = new JobDTO();
        job.setId(1L);
        job.setLocation("New York");

        when(jobRepository.findAll()).thenReturn(Collections.singletonList(job));

        Map<String, Long> statistics = jobService.getJobStatistics();

        assertNotNull(statistics);
        assertEquals(1, statistics.size());
        assertEquals(1L, statistics.get("New York"));
    }
}
