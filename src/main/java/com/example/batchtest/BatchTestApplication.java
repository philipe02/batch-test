package com.example.batchtest;

import jakarta.annotation.PostConstruct;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
@EnableBatchProcessing(isolationLevelForCreate = "ISOLATION_READ_COMMITTED",
        tablePrefix = "batch.batch_")
public class BatchTestApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bahia"));
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchTestApplication.class, args);
    }

}
