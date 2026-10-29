package io.javahub.level2.thestringconstantpoolstringbuilders;

/**
 * Demonstrates string pooling, intern() mechanics, and StringBuilder allocation behavior.
 */
public class StringPoolMechanics {

    public boolean testStringLiteralIdentity() {
        String a = "JavaMastery";
        String b = "JavaMastery";
        return a == b; // Identical pooled reference
    }

    public boolean testInternBehavior() {
        String heapString = new String("JavaMastery");
        String interned = heapString.intern();
        String literal = "JavaMastery";

        // heapString points to unique heap memory, interned points to string pool
        return heapString != literal && interned == literal;
    }

    public String buildConcatenatedCsv(int count) {
        // Pre-size capacity to avoid internal byte[] reallocations
        StringBuilder sb = new StringBuilder(count * 8);
        for (int i = 0; i < count; i++) {
            if (i > 0) sb.append(',');
            sb.append("id_").append(i);
        }
        return sb.toString();
    }
}
