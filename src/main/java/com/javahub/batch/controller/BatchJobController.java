package com.javahub.batch.controller;

import com.javahub.batch.config.CustomerBatchConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/batch")
public class BatchJobController {

    private final JobLauncher jobLauncher;
    private final Job customerIngestionJob;
    private final CustomerBatchConfig batchConfig;

    public BatchJobController(JobLauncher jobLauncher, Job customerIngestionJob, CustomerBatchConfig batchConfig) {
        this.jobLauncher = jobLauncher;
        this.customerIngestionJob = customerIngestionJob;
        this.batchConfig = batchConfig;
    }

    @PostMapping("/run")
    public ResponseEntity<Map<String, Object>> runJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(customerIngestionJob, params);

        return ResponseEntity.ok(Map.of(
            "jobId", execution.getId(),
            "status", execution.getStatus().toString(),
            "exitStatus", execution.getExitStatus().getExitCode()
        ));
    }

    @GetMapping("/results")
    public ResponseEntity<List<String>> getResults() {
        return ResponseEntity.ok(batchConfig.getProcessedRecords().stream().toList());
    }
}
