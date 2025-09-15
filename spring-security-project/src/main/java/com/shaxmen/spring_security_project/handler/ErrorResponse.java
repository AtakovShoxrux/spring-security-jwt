package com.shaxmen.spring_security_project.handler;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponse {

  private String message;

  private String code;

  private List<ValidationError> validationErrorsList;
}
