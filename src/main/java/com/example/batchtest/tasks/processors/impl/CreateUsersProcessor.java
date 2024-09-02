package com.example.batchtest.tasks.processors.impl;

import com.example.batchtest.dto.UserToCreateDTO;
import com.example.batchtest.models.UsersToCreateEntity;
import com.example.batchtest.tasks.processors.IProcessor;
import com.example.batchtest.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUsersProcessor implements IProcessor<UsersToCreateEntity, UserToCreateDTO> {
    @Override
    public ItemProcessor<UsersToCreateEntity, UserToCreateDTO> processor() {
        return new CreateUsersProcessorImpl();
    }

    private static class CreateUsersProcessorImpl implements ItemProcessor<UsersToCreateEntity, UserToCreateDTO> {
        @Override
        public UserToCreateDTO process(UsersToCreateEntity usersToCreateEntity) {
            return UserToCreateDTO.builder()
                    .person(usersToCreateEntity.getPerson())
                    .usersToCreateId(usersToCreateEntity.getId())
                    .username(UserUtils.generateUsername(usersToCreateEntity.getPerson()
                            .getName()))
                    .password(UserUtils.generateRandomPassword())
                    .build();
        }
    }
}
