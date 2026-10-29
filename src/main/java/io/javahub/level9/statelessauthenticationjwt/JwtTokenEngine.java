package io.javahub.level9.statelessauthenticationjwt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Emulates JWT header, payload Base64Url encoding, and signature verification.
 */
public class JwtTokenEngine {

    public record JwtPayload(String sub, long exp) {}

    public String generateCompactToken(String subject, long expirationEpoch) {
        String header = Base64.getUrlEncoder().withoutPadding().encodeToString("{\"alg\":\"HS256\"}".getBytes(StandardCharsets.UTF_8));
        String payloadJson = "{\"sub\":\"" + subject + "\",\"exp\":" + expirationEpoch + "}";
        String payload = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));

        String signature = Base64.getUrlEncoder().withoutPadding().encodeToString((header + "." + payload + ".SECRET").getBytes(StandardCharsets.UTF_8));
        return header + "." + payload + "." + signature;
    }

    public boolean isExpired(long tokenExpiration, long currentTime) {
        return currentTime > tokenExpiration;
    }
}
