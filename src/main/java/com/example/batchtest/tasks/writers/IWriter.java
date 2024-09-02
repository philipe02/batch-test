package com.example.batchtest.tasks.writers;

import org.springframework.batch.item.ItemWriter;

public interface IWriter<T> {
    ItemWriter<T> writer();
}
