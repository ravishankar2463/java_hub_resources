package io.javahub.level2.regularexpressionsandredosdefense;

/**
 * Level 2: Regular Expressions and ReDoS Defense
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-2/regular-expressions-and-redos-defense
 */
public class RegularexpressionsandredosdefenseLab {

    private final String topicTitle;
    private final int level;

    public RegularexpressionsandredosdefenseLab() {
        this.topicTitle = "Regular Expressions and ReDoS Defense";
        this.level = 2;
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
