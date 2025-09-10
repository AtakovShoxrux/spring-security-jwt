package com.shaxmen.spring_security_project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  USER_NOT_FOUND("USER_NOT_FOUND", "User not found with this id %s",
      HttpStatus.NOT_FOUND),
  CHANGE_PASSWORD_MISMATCH(
      "CHANGE_PASSWORD_MISMATCH", "Passwords do not match with each other %s" , HttpStatus.NOT_ACCEPTABLE);

  private final String code;

  private final String defaultMassage;

  private final HttpStatus httpStatus;

  ErrorCode(
      String code,
      String defaultMassage,
      HttpStatus httpStatus
  ) {
    this.code = code;
    this.defaultMassage = defaultMassage;
    this.httpStatus = httpStatus;
  }
}
