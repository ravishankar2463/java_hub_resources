# Spring Batch Chunk-Oriented Processing Live Application

Features:
- **Chunk-Oriented Step Execution**: Reader -> Processor -> Writer with transaction commit per chunk.
- **H2 Job Repository**: Full execution metadata tracking (JobExecution, StepExecution).
- **REST Job Trigger**: Trigger execution and inspect output via REST API.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Trigger the batch ingestion job
curl -X POST http://localhost:8080/api/v1/batch/run

# 2. Inspect transformed records processed in chunks
curl -X GET http://localhost:8080/api/v1/batch/results
```

## Automated Testing
```bash
mvn test
```
