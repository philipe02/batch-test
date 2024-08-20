package com.example.batchtest.tasks.readers;

import com.example.batchtest.models.UsersToCreateEntity;
import com.example.batchtest.repositories.UsersToCreateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;

@Slf4j
@Configuration
public class CreateUsersReader {
    /**
     * Page size and the chunk size are different concepts.
     * Page size defines how many records should be read from the table by the repository
     * and the chunk size defines commit size of the step.
     * However, it’s recommended to use the same values for both.
     */
    @Value("${batch.chunk:1}")
    private Integer chunk;

    @Bean
    RepositoryItemReader<UsersToCreateEntity> findUsersToCreate(UsersToCreateRepository usersToCreateRepository) {
        log.info("Reading users to create");
        return new RepositoryItemReaderBuilder<UsersToCreateEntity>()
                .name("findUsersToCreate")
                .repository(usersToCreateRepository)
                .methodName("findAllByCreatedFalse")
                .pageSize(chunk)
                //.saveState(true) //TODO:  test retry logic with saveState
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();
    }
}
