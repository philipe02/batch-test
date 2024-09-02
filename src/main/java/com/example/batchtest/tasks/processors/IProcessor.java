package com.example.batchtest.tasks.processors;

import org.springframework.batch.item.ItemProcessor;

public interface IProcessor<I,O> {
    ItemProcessor<I,O> processor();
}
