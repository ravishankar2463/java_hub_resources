package com.javahub.structured.service;
import com.javahub.structured.dto.CodeReviewAnalysis;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StructuredLlmParserService {
    public CodeReviewAnalysis parseCodeReview(String targetFile) {
        return new CodeReviewAnalysis(
            targetFile,
            2,
            "MEDIUM",
            List.of("Replace traditional Thread with Virtual Threads", "Use try-with-resources on MemorySegment arena")
        );
    }
}
