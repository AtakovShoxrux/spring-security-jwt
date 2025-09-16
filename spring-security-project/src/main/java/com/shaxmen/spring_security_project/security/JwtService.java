package com.shaxmen.spring_security_project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String TOKEN_TYPE = "token_type";
  private PrivateKey privateKey;
  private PublicKey publicKey;
  @Value("${jwt.access-token-expiration}")
  private Long accessTokeExpiration;
  @Value("${jwt.refresh-token-expiration}")
  private Long refreshTokenExpiration;

  @PostConstruct
  public void initKeys() {
    try {
      this.privateKey = KeyUtils.loadPrivateKey("keys/local-only/private-key.pem");
      this.publicKey = KeyUtils.loadPublicKey("keys/local-only/public-key.pem");
    } catch (Exception e) {
      throw new IllegalStateException("Failed to load RSA keys", e);
    }
  }

  public String generateAccessToken(final String username) {
    final Map<String, Object> claims = Map.of(TOKEN_TYPE, "ACCESS_TOKEN");
    return buildToken(username, claims, this.accessTokeExpiration);
  }

  public String generateRefreshToken(final String username) {
    final Map<String, Object> claims = Map.of(TOKEN_TYPE, "REFRESH_TOKEN");
    return buildToken(username, claims, this.refreshTokenExpiration);
  }

  private String buildToken(String username, Map<String, Object> claims, Long tokenExpiration) {
    return Jwts.builder()
        .claims(claims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + tokenExpiration))
        .signWith(this.privateKey)
        .compact();
  }

  public Boolean isValidToken(final String token, String expectedUsername) {
    final String username = extractUserName(token);
    return username.equals(expectedUsername) && !isTokenExpired(token);
  }

  private Boolean isTokenExpired(String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }

  public String extractUserName(String token) {
    return extractClaims(token).getSubject();
  }

  private Claims extractClaims(String token) {
    try {
      return Jwts.parser()
          .verifyWith(this.publicKey)
          .build()
          .parseSignedClaims(token)
          .getPayload();
    } catch (final JwtException e) {
      throw new RuntimeException("Invalid JWT token", e);
    }
  }

  public String refreshAccessToken(final String refreshToken) {
    final Claims claims = extractClaims(refreshToken);
    if (!"REFRESH_TOKEN".equals(claims.get(TOKEN_TYPE))) {
      throw new RuntimeException("Invalid token type");
    }
    if (isTokenExpired(refreshToken)) {
      throw new RuntimeException("Token is expired");
    }
    final String username = claims.getSubject();
    return generateAccessToken(username);
  }
}
