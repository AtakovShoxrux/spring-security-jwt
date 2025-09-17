package com.shaxmen.spring_security_project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  USER_NOT_FOUND(
      "USER_NOT_FOUND", "User not found with this id %s",
      HttpStatus.NOT_FOUND),
  CHANGE_PASSWORD_MISMATCH(
      "CHANGE_PASSWORD_MISMATCH", "Passwords do not match with each other %s",
      HttpStatus.NOT_ACCEPTABLE),
  PASSWORD_MISMATCH(
      "PASSWORD_MISMATCH", "Passwords do not match with each other",
      HttpStatus.NOT_ACCEPTABLE),
  ACCOUNT_ALREADY_DEACTIVATED(
      "ACCOUNT_ALREADY_DEACTIVATED", "Account already deactivated",
      HttpStatus.ALREADY_REPORTED),
  ACCOUNT_ALREADY_ACTIVATED(
      "ACCOUNT_ALREADY_ACTIVATED", "Account already activated",
      HttpStatus.ALREADY_REPORTED),
  EMAIL_ALREADY_EXISTS(
      "EMAIL_ALREADY_EXISTS", "This email already exists",
      HttpStatus.BAD_REQUEST),
  PHONE_NUMBER_ALREADY_EXISTS(
      "PHONE_NUMBER_ALREADY_EXISTS", "This phone number already exists",
      HttpStatus.BAD_REQUEST),
  USER_DISABLED(
      "USER_DISABLED", "Account is disabled",
      HttpStatus.UNAUTHORIZED),
  BAD_CREDENTIALS(
      "BAD_CREDENTIALS", "Username and / or password is incorrect!",
      HttpStatus.NOT_FOUND),
  USERNAME_NOT_FOUND(
      "USERNAME_NOT_FOUND", "Username not found!",
      HttpStatus.NOT_FOUND),
  INTERNAL_EXCEPTION(
      "INTERNAL_EXCEPTION", "Unexpected error occurred!",
      HttpStatus.INTERNAL_SERVER_ERROR);

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
