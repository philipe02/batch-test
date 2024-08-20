package com.example.batchtest.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendNotificationsJobImpl {

    @Bean
    Job sendNotificationsJob(
            JobRepository jobRepository,
            Flow sendNotificationFlow
    ) {
        return new JobBuilder("sendNotificationsJob", jobRepository)
                .start(sendNotificationFlow)
                .end()
                .build();
    }
}
