package com.shaxmen.spring_security_project.security;

import io.jsonwebtoken.Jwts;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String TOKEN_TYPE = "token_type";
  private final PrivateKey privateKey;
  private final PublicKey publicKey;
  @Value("${jwt.access-token-expiration}")
  private Long accessTokeExpiration;
  @Value("${jwt.refresh-token-expiration}")
  private Long refreshTokenExpiration;


  public JwtService() throws Exception {
    this.privateKey = KeyUtils.loadPrivateKey("keys/local-only/private-key.pem");
    this.publicKey = KeyUtils.loadPublicKey("keys/local-only/public-key.pem");
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
}
