package com.shaxmen.spring_security_project.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequestDto {

  @NotBlank(message = "VALIDATION.REGISTRATION.FIRSTNAME.NOT_BLANK")
  @Size(min = 3, max = 35, message = "VALIDATION.REGISTRATION.FIRSTNAME.NOT_BLANK")
  @Pattern(
      regexp = "^[\\p{L} '-]+$",
      message = "VALIDATION.REGISTRATION.FIRSTNAME.PATTERN"
  )
  @Schema(example = "Morning")
  private String firstName;

  @NotBlank(message = "VALIDATION.REGISTRATION.LASTNAME.NOT_BLANK")
  @Size(min = 3, max = 35, message = "VALIDATION.REGISTRATION.LASTNAME.NOT_BLANK")
  @Pattern(
      regexp = "^[\\p{L} '-]+$",
      message = "VALIDATION.REGISTRATION.LASTNAME.NOT_BLANK"
  )
  @Schema(example = "Star")
  private String lastName;

  @NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.NOT_BLANK")
  @Email(message = "VALIDATION.REGISTRATION.EMAIL.NOT_BLANK")
  // @IsValidEmail(message = "VALIDATION.REGISTRATION.EMAIL.IS_VALID_EMAIL")
  @Schema(example = "morningstar@gmail.com")
  private String email;

  @NotBlank(message = "VALIDATION.REGISTRATION.PHONE.NOT_BLANK")
  @Pattern(
      regexp = "^\\+?[0-9]{10,13}$",
      message = "VALIDATION.REGISTRATION.PHONE.NOT_BLANK"
  )
  @Schema(example = "+77896541233")
  private String phoneNumber;

  @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
  @Size(
      min = 8,
      max = 35,
      message = "VALIDATION.REGISTRATION.PASSWORD.SIZE"
  )
  @Pattern(
      regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).$",
      message = "VALIDATION.REGISTRATION.PASSWORD.WEAK"
  )
  @Schema(example = "<PASSWORD>")
  private String password;

  @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
  @Size(
      min = 8,
      max = 35,
      message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK"
  )
  @Schema(example = "<PASSWORD>")
  private String confirmPassword;
}
