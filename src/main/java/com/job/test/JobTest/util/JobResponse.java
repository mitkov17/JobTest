package com.job.test.JobTest.util;

import com.job.test.JobTest.dto.JobDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobResponse {
    private List<JobDTO> data;
}
