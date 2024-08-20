package com.example.batchtest.tasks.flows;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUsersFlowImpl {

    @Bean
    Flow createUsersFlow(Step createUsersStep) {
        return new FlowBuilder<SimpleFlow>("createUsersFlow")
                .start(createUsersStep)
                .build();
    }
}
