package com.example.batchtest.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class JobRunConfig {

    private final JobLauncher jobLauncher;
    private final Job createUsersJob;
    private final Job sendNotificationsJob;

    @Scheduled(fixedRateString = "#{${batch.create-users-job.run-interval-in-seconds}}", timeUnit = TimeUnit.SECONDS)
    public void runCreateUsersJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters createUsersJobParameters = createJobParameters(sendNotificationsJob);
        jobLauncher.run(createUsersJob, createUsersJobParameters);
        log.info("Job {} started", createUsersJob.getName());
    }

    @Scheduled(fixedRateString = "#{${batch.send-notifications.run-interval-in-seconds}}", timeUnit = TimeUnit.SECONDS, initialDelay = 30)
    public void runSendNotificationsJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters sendNotificationsJobParameters = createJobParameters(sendNotificationsJob);
        jobLauncher.run(sendNotificationsJob, sendNotificationsJobParameters);
        log.info("Job {} started", sendNotificationsJob.getName());
    }

    private JobParameters createJobParameters(Job job) {
        return new JobParametersBuilder().addString("JobID", job.getName() + "_" + System.currentTimeMillis())
                .toJobParameters();
    }

}
