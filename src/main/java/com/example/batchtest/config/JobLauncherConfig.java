package com.example.batchtest.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class JobLauncherConfig {

    @Bean
    public JobLauncher jobLauncher(
            ThreadPoolTaskExecutor threadPoolTaskExecutor,
            JobRepository jobRepository
    ) {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setTaskExecutor(threadPoolTaskExecutor);
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }
}
