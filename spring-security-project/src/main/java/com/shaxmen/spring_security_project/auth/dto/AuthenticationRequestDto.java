package com.shaxmen.spring_security_project.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequestDto {

  @NotBlank(message = "VALIDATION.AUTHENTICATION.EMAIL.NOT_BLANK")
  @Email(message = "VALIDATION.AUTHENTICATION.EMAIL.FORMAT")
  @Schema(example = "jo'rabek@gmail.com")
  private String email;
  @NotBlank(message = "VALIDATION.AUTHENTICATION.PASSWORD.NOT_BLANK")
  @Schema(example = "<PASSWORD>")
  private String password;
}
