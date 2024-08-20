package com.example.batchtest.services;

import com.example.batchtest.models.UsersToCreateEntity;
import com.example.batchtest.repositories.UsersToCreateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsersToCreateService {

    private final Clock clock;
    private final UsersToCreateRepository usersToCreateRepository;


    public UsersToCreateEntity updateUserToCreate(Integer id) {
        UsersToCreateEntity usersToCreateEntity = usersToCreateRepository.findById(id).orElseThrow();
        usersToCreateEntity.setCreated(true);
        usersToCreateEntity.setCreatedAt(LocalDateTime.now(clock));

        return usersToCreateRepository.save(usersToCreateEntity);
    }
}
