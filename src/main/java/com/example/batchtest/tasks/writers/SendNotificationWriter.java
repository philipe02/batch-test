package com.example.batchtest.tasks.writers;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendNotificationWriter implements ItemWriter<String> {
    @Override
    public void write(
            @Nonnull
            Chunk<? extends String> chunk) {
        log.info("Writing notifications to database");

        //TODO: Implement send notifications to users

        log.info("Finished writing notifications to database");
    }
}
