package com.javahub.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
public class CustomerBatchConfig {

    private static final Logger log = LoggerFactory.getLogger(CustomerBatchConfig.class);
    private final ConcurrentLinkedQueue<String> processedRecords = new ConcurrentLinkedQueue<>();

    public record RawRecord(String name, String email) {}
    public record ProcessedRecord(String name, String email, String status) {}

    @Bean
    public ItemReader<RawRecord> sampleReader() {
        return new ListItemReader<>(List.of(
            new RawRecord("alice", "alice@test.com"),
            new RawRecord("bob", "bob@test.com"),
            new RawRecord("carol", "carol@test.com"),
            new RawRecord("dave", "dave@test.com"),
            new RawRecord("eve", "eve@test.com")
        ));
    }

    @Bean
    public ItemProcessor<RawRecord, ProcessedRecord> sampleProcessor() {
        return raw -> {
            log.info(">> [BATCH_PROCESSOR] Transforming {}", raw.name());
            return new ProcessedRecord(raw.name().toUpperCase(), raw.email(), "ACTIVE");
        };
    }

    @Bean
    public ItemWriter<ProcessedRecord> sampleWriter() {
        return chunk -> {
            log.info(">> [BATCH_WRITER] Writing chunk of {} items", chunk.size());
            chunk.forEach(p -> processedRecords.add(p.name() + " (" + p.email() + ")"));
        };
    }

    @Bean
    public Step customerIngestionStep(
            JobRepository jobRepository,
            PlatformTransactionManager txManager,
            ItemReader<RawRecord> reader,
            ItemProcessor<RawRecord, ProcessedRecord> processor,
            ItemWriter<ProcessedRecord> writer) {
        return new StepBuilder("customerIngestionStep", jobRepository)
                .<RawRecord, ProcessedRecord>chunk(2, txManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job customerIngestionJob(JobRepository jobRepository, Step customerIngestionStep) {
        return new JobBuilder("customerIngestionJob", jobRepository)
                .start(customerIngestionStep)
                .build();
    }

    public ConcurrentLinkedQueue<String> getProcessedRecords() {
        return processedRecords;
    }
}
