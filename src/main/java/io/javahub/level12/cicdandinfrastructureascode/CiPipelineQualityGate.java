package io.javahub.level12.cicdandinfrastructureascode;

/**
 * Automated CI/CD pipeline quality gate evaluator.
 */
public class CiPipelineQualityGate {

    public record BuildReport(int totalTests, int failedTests, double codeCoverage) {}

    public boolean passesQualityGate(BuildReport report) {
        return report.failedTests() == 0 && report.codeCoverage() >= 80.0;
    }
}
