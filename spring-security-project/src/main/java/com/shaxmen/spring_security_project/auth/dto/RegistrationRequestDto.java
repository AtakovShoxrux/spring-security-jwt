package com.shaxmen.spring_security_project.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequestDto {

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String password;
  private String confirmPassword;
}
