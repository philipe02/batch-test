package com.example.batchtest.tasks.readers.impl;

import com.example.batchtest.models.NotificationsToSendEntity;
import com.example.batchtest.repositories.NotificationsToSendRepository;
import com.example.batchtest.tasks.readers.IReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendNotificationReader implements IReader<NotificationsToSendEntity> {
    @Value("${batch.chunk:1}")
    private Integer chunk;
    private final NotificationsToSendRepository notificationsToSendRepository;

    @Override
    public ItemReader<NotificationsToSendEntity> reader() {
        return new SendNotificationReaderImpl().findNotificationsToSend();
    }

    private class SendNotificationReaderImpl {
        /**
         * Page size and the chunk size are different concepts.
         * Page size defines how many records should be read from the table by the repository
         * and the chunk size defines commit size of the step.
         * However, it’s recommended to use the same values for both.
         */

        ItemReader<NotificationsToSendEntity> findNotificationsToSend() {
            log.info("Reading notifications to send");
            return new RepositoryItemReaderBuilder<NotificationsToSendEntity>()
                    .name("findNotificationsToSend")
                    .repository(notificationsToSendRepository)
                    .methodName("findAllBySentFalse")
                    .pageSize(chunk)
                    .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                    .build();
        }
    }
}
