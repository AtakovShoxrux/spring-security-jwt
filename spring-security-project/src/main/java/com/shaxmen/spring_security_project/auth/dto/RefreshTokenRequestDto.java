package com.shaxmen.spring_security_project.auth.dto;

import lombok.Builder;


@Builder
public class RefreshTokenRequestDto {

  private String refreshToken;

  public RefreshTokenRequestDto() {
  }

  public RefreshTokenRequestDto(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
