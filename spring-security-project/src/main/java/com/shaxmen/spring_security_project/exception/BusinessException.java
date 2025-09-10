package com.shaxmen.spring_security_project.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private final ErrorCode errorCode;

  private final Object[] args;

  public BusinessException(final ErrorCode errorCode, final Object... args) {
    super(getFormattedMessage(errorCode, args));
    this.errorCode = errorCode;
    this.args = args;
  }

  private static String getFormattedMessage(ErrorCode errorCode, Object[] args) {
    if (args != null || args.length > 0) {
      return String.format(errorCode.getDefaultMassage(), args);
    }
    return errorCode.getDefaultMassage();
  }

}
