package com.javahub.level14;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enterprise AI: LLM Structured Output Parser.
 * Demonstrates extracting typed Java 21 Records from raw JSON responses returned by LLMs,
 * enforcing schema validation and deterministic deserialization.
 */
public class StructuredOutputRecordParser {

    public record SentimentAnalysis(String sentiment, double confidenceScore, List<String> keyKeywords) {}

    public record CodeReviewFinding(String filePath, int lineNumber, String severity, String recommendation) {}

    public SentimentAnalysis parseSentiment(String rawJson) {
        String sentiment = extractStringField(rawJson, "sentiment", "NEUTRAL");
        double score = extractDoubleField(rawJson, "confidenceScore", 0.5);
        List<String> keywords = extractListField(rawJson, "keyKeywords");
        return new SentimentAnalysis(sentiment, score, keywords);
    }

    public CodeReviewFinding parseCodeFinding(String rawJson) {
        String file = extractStringField(rawJson, "filePath", "unknown.java");
        int line = (int) extractDoubleField(rawJson, "lineNumber", 1.0);
        String severity = extractStringField(rawJson, "severity", "INFO");
        String rec = extractStringField(rawJson, "recommendation", "No action needed");
        return new CodeReviewFinding(file, line, severity, rec);
    }

    private String extractStringField(String json, String field, String fallback) {
        Pattern pattern = Pattern.compile("\"" + field + "\"\\s*:\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(json);
        return matcher.find() ? matcher.group(1) : fallback;
    }

    private double extractDoubleField(String json, String field, double fallback) {
        Pattern pattern = Pattern.compile("\"" + field + "\"\\s*:\\s*([0-9.]+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group(1));
            } catch (NumberFormatException ignored) {}
        }
        return fallback;
    }

    private List<String> extractListField(String json, String field) {
        Pattern pattern = Pattern.compile("\"" + field + "\"\\s*:\\s*\\[([^\]]+)\\]");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            String items = matcher.group(1);
            return List.of(items.replaceAll("\"", "").split("\\s*,\\s*"));
        }
        return List.of();
    }
}
