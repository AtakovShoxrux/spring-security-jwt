package com.shaxmen.spring_security_project.user.dto;

import java.time.LocalDate;
import lombok.Builder;


@Builder
public class ProfileUpdateRequestDto {

  private String firstName;

  private String lastName;

  private LocalDate dateOfBirth;

  public ProfileUpdateRequestDto() {
  }

  public ProfileUpdateRequestDto(
      String firstName,
      String lastName,
      LocalDate dateOfBirth
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}
