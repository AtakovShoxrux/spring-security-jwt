package com.shaxmen.spring_security_project.handler;

import static com.shaxmen.spring_security_project.exception.ErrorCode.BAD_CREDENTIALS;
import static com.shaxmen.spring_security_project.exception.ErrorCode.INTERNAL_EXCEPTION;
import static com.shaxmen.spring_security_project.exception.ErrorCode.USERNAME_NOT_FOUND;
import static com.shaxmen.spring_security_project.exception.ErrorCode.USER_DISABLED;

import com.shaxmen.spring_security_project.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class ApplicationExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleException(final BusinessException exception) {
    log.debug(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse
        .builder()
        .code(exception.getErrorCode().getCode())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DisabledException.class)
  public ResponseEntity<ErrorResponse> handleException(final DisabledException exception) {
    log.debug(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(USER_DISABLED.getCode())
        .message(USER_DISABLED.getDefaultMassage())
        .build();
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleException(final BadCredentialsException exception) {
    log.debug(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(BAD_CREDENTIALS.getCode())
        .message(BAD_CREDENTIALS.getDefaultMassage())
        .message(BAD_CREDENTIALS.getDefaultMassage())
        .build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(final UsernameNotFoundException exception) {
    log.debug(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(USERNAME_NOT_FOUND.getCode())
        .message(USERNAME_NOT_FOUND.getDefaultMassage())
        .build();
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(final EntityNotFoundException exception) {
    log.debug(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(exception.getCause().getMessage())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
    log.error(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(INTERNAL_EXCEPTION.getCode())
        .message(INTERNAL_EXCEPTION.getDefaultMassage())
        .build();
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}