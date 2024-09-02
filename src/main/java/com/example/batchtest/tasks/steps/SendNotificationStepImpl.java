package com.example.batchtest.tasks.steps;

import com.example.batchtest.models.NotificationsToSendEntity;
import com.example.batchtest.tasks.readers.IReader;
import com.example.batchtest.tasks.writers.IWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SendNotificationStepImpl {

    @Value("${batch.chunk:1}")
    private Integer chunk;

    @Bean
    Step sendNotificationStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            IReader<NotificationsToSendEntity> sendNotificationReader,
            IWriter<String> sendNotificationWriter
    ) {
        return new StepBuilder("sendNotificationStep", jobRepository)
                .<NotificationsToSendEntity, String>chunk(chunk, transactionManager)
                .reader(sendNotificationReader.reader())
                .writer(sendNotificationWriter.writer())
                .build();
    }

}
