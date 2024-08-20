package com.example.batchtest.services;

import com.example.batchtest.dto.UserToCreateDTO;
import com.example.batchtest.models.UserEntity;
import com.example.batchtest.repositories.UserRepository;
import com.example.batchtest.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createUser(UserToCreateDTO user) {
        UserEntity userEntity = UserEntity.builder()
                .person(user.getPerson())
                .username(user.getUsername())
                .password(UserUtils.encodePassword(user.getPassword()))
                .build();

        return userRepository.save(userEntity);
    }
}
