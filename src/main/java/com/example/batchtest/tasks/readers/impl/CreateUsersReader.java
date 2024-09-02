package com.example.batchtest.tasks.readers.impl;

import com.example.batchtest.models.UsersToCreateEntity;
import com.example.batchtest.repositories.UsersToCreateRepository;
import com.example.batchtest.tasks.readers.IReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUsersReader implements IReader<UsersToCreateEntity> {
    @Value("${batch.chunk:1}")
    private Integer chunk;
    private final UsersToCreateRepository usersToCreateRepository;

    @Override
    public ItemReader<UsersToCreateEntity> reader() {
        return new CreateUsersReaderImpl().findUsersToCreate();
    }

    private class CreateUsersReaderImpl {
        /**
         * Page size and the chunk size are different concepts.
         * Page size defines how many records should be read from the table by the repository
         * and the chunk size defines commit size of the step.
         * However, it’s recommended to use the same values for both.
         */

        RepositoryItemReader<UsersToCreateEntity> findUsersToCreate() {
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
}
