package com.job.test.JobTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JobTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobTestApplication.class, args);
	}

}
