package socializer.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class JwtUtil implements Serializable {

    private static final String PORTAL_USER_ID_CLAIM_NAME = "PORTAL-USER-ID";
    public static final long JWT_TOKEN_VALIDITY = 60L * 60L * 1000L; // 1 hour in millis

    public static String generateToken(Key secretKey, Long portalUserId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(PORTAL_USER_ID_CLAIM_NAME, portalUserId);

        long currentTimeMillis = System.currentTimeMillis();
        Date issuedAt = new Date(currentTimeMillis);
        Date expiration = new Date(currentTimeMillis + JWT_TOKEN_VALIDITY);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public static Long validateTokenAndGetPortalUserId(Key secretKey, String token)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
            SignatureException, IllegalArgumentException
    {
        Claims claims;
        claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get(PORTAL_USER_ID_CLAIM_NAME, Long.class);
    }

}
