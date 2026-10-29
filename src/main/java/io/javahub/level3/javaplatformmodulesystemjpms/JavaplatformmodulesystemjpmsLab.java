package io.javahub.level3.javaplatformmodulesystemjpms;

/**
 * Level 3: Java Platform Module System (JPMS)
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-3/java-platform-module-system-jpms
 */
public class JavaplatformmodulesystemjpmsLab {

    private final String topicTitle;
    private final int level;

    public JavaplatformmodulesystemjpmsLab() {
        this.topicTitle = "Java Platform Module System (JPMS)";
        this.level = 3;
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
