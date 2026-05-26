package io.brlrlr.lib.auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.brlrlr.lib.auth.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenProvider {
	private final JwtProperties properties;
    private final SecretKey key;

    public TokenProvider(JwtProperties properties) {
        this.properties = properties;
        this.key = Keys.hmacShaKeyFor(properties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + properties.getAccessTokenExpirationMs()))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            if (properties.getRequiredClaims() != null) {
                for (String claim : properties.getRequiredClaims()) {
                    if (!claims.containsKey(claim)) {
                        return false; 
                    }
                }
            }
            return true;
    }
    
    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key) 
                .build()
                .parseSignedClaims(token) 
                .getPayload(); 

        return claims.getSubject();
    }

}
