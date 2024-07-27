package com.job.test.JobTest.repositories;

import com.job.test.JobTest.dto.JobDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobDTO, Long> {
}
