package com.example.batchtest.services;

import com.example.batchtest.models.NotificationEntity;
import com.example.batchtest.models.UserEntity;
import com.example.batchtest.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationEntity createNotificationForUser(UserEntity userEntity, String message) {
        NotificationEntity notification = NotificationEntity.builder()
                .user(userEntity)
                .message(message)
                .build();

        return notificationRepository.save(notification);
    }

}
