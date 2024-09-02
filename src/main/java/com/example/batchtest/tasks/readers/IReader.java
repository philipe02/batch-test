package com.example.batchtest.tasks.readers;

import org.springframework.batch.item.ItemReader;

public interface IReader<T> {
    ItemReader<T> reader();
}
