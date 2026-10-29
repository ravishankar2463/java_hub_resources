package io.javahub.level13.productionmetricswithmicrometerandprometheus;

/**
 * Level 13: Production Metrics with Micrometer and Prometheus
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-13/production-metrics-with-micrometer-and-prometheus
 */
public class ProductionmetricswithmicrometerandprometheusLab {

    private final String topicTitle;
    private final int level;

    public ProductionmetricswithmicrometerandprometheusLab() {
        this.topicTitle = "Production Metrics with Micrometer and Prometheus";
        this.level = 13;
    }

    public String execute() {
        return "Executing JavaHub Lab for Level " + level + ": " + topicTitle;
    }

    public int getLevel() {
        return level;
    }

    public String getTopicTitle() {
        return topicTitle;
    }
}
