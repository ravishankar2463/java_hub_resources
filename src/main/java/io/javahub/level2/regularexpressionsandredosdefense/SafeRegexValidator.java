package io.javahub.level2.regularexpressionsandredosdefense;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ReDoS-hardened regular expression matching engine.
 */
public class SafeRegexValidator {

    // Uses possessive quantifier '++' to eliminate catastrophic backtracking
    private static final Pattern SAFE_EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]++@[a-zA-Z0-9.-]++\\.[a-zA-Z]{2,}+$");

    public boolean isValidEmail(String email) {
        if (email == null || email.length() > 254) { // RFC 5321 length guard
            return false;
        }
        return SAFE_EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean safeTimeoutMatch(String input, Pattern pattern, long maxMillis) {
        long startTime = System.currentTimeMillis();
        CharSequence timeoutSeq = new CharSequence() {
            @Override
            public int length() { return input.length(); }
            @Override
            public char charAt(int index) {
                if (System.currentTimeMillis() - startTime > maxMillis) {
                    throw new RuntimeException("Regex execution timeout exceeded (ReDoS defense)");
                }
                return input.charAt(index);
            }
            @Override
            public CharSequence subSequence(int start, int end) {
                return input.subSequence(start, end);
            }
        };

        Matcher matcher = pattern.matcher(timeoutSeq);
        return matcher.find();
    }
}
