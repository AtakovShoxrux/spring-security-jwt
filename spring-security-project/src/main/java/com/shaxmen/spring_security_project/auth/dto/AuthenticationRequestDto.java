package com.shaxmen.spring_security_project.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public class AuthenticationRequestDto {

  @NotBlank(message = "VALIDATION.AUTHENTICATION.EMAIL.NOT_BLANK")
  @Email(message = "VALIDATION.AUTHENTICATION.EMAIL.FORMAT")
  @Schema(example = "jo'rabek@gmail.com")
  private String email;
  @NotBlank(message = "VALIDATION.AUTHENTICATION.PASSWORD.NOT_BLANK")
  @Schema(example = "<PASSWORD>")
  private String password;

  public AuthenticationRequestDto() {
  }

  public AuthenticationRequestDto(String password, String email) {
    this.password = password;
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
