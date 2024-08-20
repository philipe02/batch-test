package com.example.batchtest.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUsersJobImpl {

    @Bean
    Job createUsersJob(
            JobRepository jobRepository,
            Flow createUsersFlow
            ) {
       return new JobBuilder("createUsersJob", jobRepository)
               .start(createUsersFlow)
               .end()
               .build();

    }
}
