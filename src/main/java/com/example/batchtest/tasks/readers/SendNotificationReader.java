package com.example.batchtest.tasks.readers;

import com.example.batchtest.models.NotificationsToSendEntity;
import com.example.batchtest.repositories.NotificationsToSendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;

@Slf4j
@Configuration
public class SendNotificationReader {
    /**
     * Page size and the chunk size are different concepts.
     * Page size defines how many records should be read from the table by the repository
     * and the chunk size defines commit size of the step.
     * However, it’s recommended to use the same values for both.
     */
    @Value("${batch.chunk:1}")
    private Integer chunk;

    @Bean
    ItemReader<NotificationsToSendEntity> findNotificationsToSend(NotificationsToSendRepository notificationsToSendRepository) {
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
