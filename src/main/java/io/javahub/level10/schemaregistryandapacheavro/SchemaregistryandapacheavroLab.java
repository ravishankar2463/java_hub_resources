package io.javahub.level10.schemaregistryandapacheavro;

/**
 * Level 10: Schema Registry and Apache Avro
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-10/schema-registry-and-apache-avro
 */
public class SchemaregistryandapacheavroLab {

    private final String topicTitle;
    private final int level;

    public SchemaregistryandapacheavroLab() {
        this.topicTitle = "Schema Registry and Apache Avro";
        this.level = 10;
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
