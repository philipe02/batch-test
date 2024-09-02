package com.example.batchtest.tasks.steps;

import com.example.batchtest.dto.UserToCreateDTO;
import com.example.batchtest.models.UsersToCreateEntity;
import com.example.batchtest.tasks.processors.IProcessor;
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
public class CreateUsersStepImpl {

    @Value("${batch.chunk:1}")
    private Integer chunk;

    @Bean
    Step createUsersStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            IReader<UsersToCreateEntity> createUsersReader,
            IProcessor<UsersToCreateEntity, UserToCreateDTO> createUsersProcessor,
            IWriter<UserToCreateDTO> createUsersWriter
    ) {
        return new StepBuilder("createUsersStep", jobRepository)
                .<UsersToCreateEntity, UserToCreateDTO>chunk(chunk, transactionManager)
                .reader(createUsersReader.reader())
                .processor(createUsersProcessor.processor())
                .writer(createUsersWriter.writer())
                .build();
    }

}
