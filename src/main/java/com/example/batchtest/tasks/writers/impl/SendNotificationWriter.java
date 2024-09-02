package com.example.batchtest.tasks.writers.impl;

import com.example.batchtest.tasks.writers.IWriter;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendNotificationWriter implements IWriter<String> {
    @Override
    public ItemWriter<String> writer() {
        return new SendNotificationWriterImpl();
    }

    public static class SendNotificationWriterImpl implements ItemWriter<String> {
        @Override
        public void write(
                @Nonnull
                Chunk<? extends String> chunk) {
            log.info("Writing notifications to database");

            //TODO: Implement send notifications to users

            log.info("Finished writing notifications to database");
        }
    }
}
