package io.javahub.level8.aspectorientedprogrammingaop;

/**
 * Level 8: Aspect-Oriented Programming (AOP)
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-8/aspect-oriented-programming-aop
 */
public class AspectorientedprogrammingaopLab {

    private final String topicTitle;
    private final int level;

    public AspectorientedprogrammingaopLab() {
        this.topicTitle = "Aspect-Oriented Programming (AOP)";
        this.level = 8;
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
