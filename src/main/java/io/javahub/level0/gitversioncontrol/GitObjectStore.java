package io.javahub.level0.gitversioncontrol;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Emulates Git's SHA-1 content-addressable object database (Blobs, Trees, Commits).
 */
public class GitObjectStore {

    public sealed interface GitObject permits Blob, Tree, Commit {
        String sha1();
    }

    public record Blob(String sha1, byte[] content) implements GitObject {
        public static Blob create(String text) {
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            String headerAndData = "blob " + bytes.length + "\0" + text;
            return new Blob(computeSha1(headerAndData), bytes);
        }
    }

    public record TreeEntry(String mode, String name, String targetSha1) {}

    public record Tree(String sha1, List<TreeEntry> entries) implements GitObject {
        public static Tree create(List<TreeEntry> entries) {
            StringBuilder sb = new StringBuilder();
            for (TreeEntry entry : entries) {
                sb.append(entry.mode()).append(" ").append(entry.name()).append("\0").append(entry.targetSha1());
            }
            return new Tree(computeSha1("tree " + sb.length() + "\0" + sb), List.copyOf(entries));
        }
    }

    public record Commit(String sha1, String treeSha1, Optional<String> parentSha1, String author, String message) implements GitObject {
        public static Commit create(String treeSha1, Optional<String> parentSha1, String author, String message) {
            String payload = "tree " + treeSha1 + "\n" +
                             parentSha1.map(p -> "parent " + p + "\n").orElse("") +
                             "author " + author + "\n\n" + message;
            return new Commit(computeSha1("commit " + payload.length() + "\0" + payload), treeSha1, parentSha1, author, message);
        }
    }

    private final Map<String, GitObject> database = new HashMap<>();

    public void put(GitObject obj) {
        database.put(obj.sha1(), obj);
    }

    public Optional<GitObject> get(String sha1) {
        return Optional.ofNullable(database.get(sha1));
    }

    public static String computeSha1(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm unavailable", e);
        }
    }
}
