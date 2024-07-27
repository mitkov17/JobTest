package com.job.test.JobTest.controllers;

import com.job.test.JobTest.dto.JobDTO;
import com.job.test.JobTest.services.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Test
    public void testGetTop10Jobs() throws Exception {
        JobDTO job = new JobDTO();
        job.setId(1L);
        job.setTitle("Software Engineer");

        List<JobDTO> jobs = Collections.singletonList(job);

        when(jobService.getTop10Jobs()).thenReturn(jobs);

        mockMvc.perform(get("/api/job/top10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(job.getTitle())));
    }

    @Test
    public void testGetJobStatistics() throws Exception {
        when(jobService.getJobStatistics()).thenReturn(Collections.singletonMap("Location1", 1L));

        mockMvc.perform(get("/api/job/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Location1", is(1)));
    }
}
