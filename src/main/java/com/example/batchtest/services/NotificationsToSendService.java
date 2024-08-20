package com.example.batchtest.services;

import com.example.batchtest.models.NotificationEntity;
import com.example.batchtest.models.NotificationsToSendEntity;
import com.example.batchtest.repositories.NotificationsToSendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationsToSendService {

    private final NotificationsToSendRepository notificationsToSendRepository;

    public NotificationsToSendEntity createNotificationToSend(NotificationEntity notificationEntity) {
        NotificationsToSendEntity notificationToSend = NotificationsToSendEntity.builder()
                .notification(notificationEntity)
                .build();

        return notificationsToSendRepository.save(notificationToSend);
    }
}
