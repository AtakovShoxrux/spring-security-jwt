package com.shaxmen.spring_security_project.user.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateRequestDto {

  private String firstName;

  private String lastName;

  private LocalDate dateOfBirth;
}
