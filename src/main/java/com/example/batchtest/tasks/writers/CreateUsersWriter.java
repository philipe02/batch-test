package com.example.batchtest.tasks.writers;

import com.example.batchtest.dto.UserToCreateDTO;
import com.example.batchtest.models.NotificationEntity;
import com.example.batchtest.models.NotificationsToSendEntity;
import com.example.batchtest.models.UserEntity;
import com.example.batchtest.models.UsersToCreateEntity;
import com.example.batchtest.services.NotificationService;
import com.example.batchtest.services.NotificationsToSendService;
import com.example.batchtest.services.UserService;
import com.example.batchtest.services.UsersToCreateService;
import com.example.batchtest.utils.MessageConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUsersWriter implements ItemWriter<UserToCreateDTO> {

    private final UserService userService;
    private final UsersToCreateService usersToCreateService;
    private final NotificationService notificationService;
    private final NotificationsToSendService notificationsToSendService;

    @Override
    public void write(Chunk<? extends UserToCreateDTO> chunk) {
        log.info("Writing users to database");
        List<UserToCreateDTO> users = chunk.getItems()
                .stream()
                .map(item -> (UserToCreateDTO) item)
                .toList();

        users.forEach(user -> {
            log.info("User: {}", user);
            UserEntity createdUser = userService.createUser(user);

            //TODO: Send password safely
            //Save notification
            NotificationEntity createdNotification = notificationService.createNotificationForUser(createdUser,
                    MessageConstants.USER_CREATED + " Password:" + user.getPassword());
            //Save notifications_to_send
            NotificationsToSendEntity notificationToSend = notificationsToSendService.createNotificationToSend(
                    createdNotification);
            //Update users_to_create
            UsersToCreateEntity updatedUserToCreate = usersToCreateService.updateUserToCreate(user.getUsersToCreateId());

            log.info("User created: {}", createdUser);
            log.info("User to create updated: {}", updatedUserToCreate);
            log.info("Notification created: {}", createdNotification);
            log.info("Notification to send created: {}", notificationToSend);
        });
        log.info("Finished writing users to database");
    }
}
