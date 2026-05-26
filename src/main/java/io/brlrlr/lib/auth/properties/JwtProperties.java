package io.brlrlr.lib.auth.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.auth.jwt")
public class JwtProperties {
	private String secret;
    private long accessTokenExpirationMs = 600000; //15 mins
    private long refreshTokenExpirationMs = 604800000; //7 days
    private List<String> requiredClaims;

    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }

    public long getAccessTokenExpirationMs() { return accessTokenExpirationMs; }
    public void setAccessTokenExpirationMs(long accessTokenExpirationMs) { this.accessTokenExpirationMs = accessTokenExpirationMs; }

    public long getRefreshTokenExpirationMs() { return refreshTokenExpirationMs; }
    public void setRefreshTokenExpirationMs(long refreshTokenExpirationMs) { this.refreshTokenExpirationMs = refreshTokenExpirationMs; }

    public List<String> getRequiredClaims() { return requiredClaims; }
    public void setRequiredClaims(List<String> requiredClaims) { this.requiredClaims = requiredClaims; }
    
}
