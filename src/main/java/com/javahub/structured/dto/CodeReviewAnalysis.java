package com.javahub.structured.dto;
import java.util.List;
public record CodeReviewAnalysis(String filePath, int totalIssues, String severity, List<String> recommendations) {}
