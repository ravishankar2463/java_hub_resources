package io.javahub.level2.thestringconstantpoolstringbuilders;

/**
 * Level 2: The String Constant Pool & StringBuilders
 * Official Hands-On Laboratory Implementation.
 * 
 * Curriculum Link: https://ravishankar2463.github.io/java_hub/roadmap/level-2/the-string-constant-pool-stringbuilders
 */
public class ThestringconstantpoolstringbuildersLab {

    private final String topicTitle;
    private final int level;

    public ThestringconstantpoolstringbuildersLab() {
        this.topicTitle = "The String Constant Pool & StringBuilders";
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
