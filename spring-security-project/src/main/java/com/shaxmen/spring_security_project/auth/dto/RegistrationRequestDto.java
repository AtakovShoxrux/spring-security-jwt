package com.shaxmen.spring_security_project.auth.dto;

import com.shaxmen.spring_security_project.validation.IsValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public class RegistrationRequestDto {

  @NotBlank(message = "VALIDATION.REGISTRATION.FIRSTNAME.BLANK")
  @Size(min = 3, max = 35, message = "VALIDATION.REGISTRATION.FIRSTNAME.SIZE")
  @Pattern(
      regexp = "^[\\p{L} '-]+$",
      message = "VALIDATION.REGISTRATION.FIRSTNAME.PATTERN"
  )
  @Schema(example = "Morning")
  private String firstName;

  @NotBlank(message = "VALIDATION.REGISTRATION.LASTNAME.BLANK")
  @Size(min = 3, max = 35, message = "VALIDATION.REGISTRATION.LASTNAME.SIZE")
  @Pattern(
      regexp = "^[\\p{L} '-]+$",
      message = "VALIDATION.REGISTRATION.LASTNAME.PATTERN"
  )
  @Schema(example = "Star")
  private String lastName;

  @NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.BLANK")
  @Email(message = "VALIDATION.REGISTRATION.EMAIL.FORMAT")
  @IsValidEmail(massage = "VALIDATION.REGISTRATION.EMAIL.IS_VALID_EMAIL")
  @Schema(example = "morningstar@gmail.com")
  private String email;

  @NotBlank(message = "VALIDATION.REGISTRATION.PHONE.BLANK")
  @Pattern(
      regexp = "^\\+?[0-9]{10,13}$",
      message = "VALIDATION.REGISTRATION.PHONE.FORMAT"
  )
  @Schema(example = "+77896541233")
  private String phoneNumber;

  @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.BLANK")
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

  @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.BLANK")
  @Size(
      min = 8,
      max = 35,
      message = "VALIDATION.REGISTRATION.PASSWORD.SIZE"
  )
  @Schema(example = "<PASSWORD>")
  private String confirmPassword;

  public RegistrationRequestDto() {
  }

  public RegistrationRequestDto(String firstName, String lastName, String email, String phoneNumber,
      String password, String confirmPassword) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
