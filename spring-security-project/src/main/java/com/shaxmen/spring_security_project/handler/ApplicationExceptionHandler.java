package com.shaxmen.spring_security_project.handler;

import static com.shaxmen.spring_security_project.exception.ErrorCode.BAD_CREDENTIALS;
import static com.shaxmen.spring_security_project.exception.ErrorCode.INTERNAL_EXCEPTION;
import static com.shaxmen.spring_security_project.exception.ErrorCode.USERNAME_NOT_FOUND;
import static com.shaxmen.spring_security_project.exception.ErrorCode.USER_DISABLED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.shaxmen.spring_security_project.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class ApplicationExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleException(final BusinessException exception) {
    log.error(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse
        .builder()
        .code(exception.getErrorCode().getCode())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(response, BAD_REQUEST);
  }

  @ExceptionHandler(DisabledException.class)
  public ResponseEntity<ErrorResponse> handleException(final DisabledException exception) {
    log.error(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(USER_DISABLED.getCode())
        .message(USER_DISABLED.getDefaultMassage())
        .build();
    return new ResponseEntity<>(response, UNAUTHORIZED);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleException(final BadCredentialsException exception) {
    log.error(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(BAD_CREDENTIALS.getCode())
        .message(BAD_CREDENTIALS.getDefaultMassage())
        .message(BAD_CREDENTIALS.getDefaultMassage())
        .build();
    return new ResponseEntity<>(response, BAD_REQUEST);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(final UsernameNotFoundException exception) {
    log.error(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(USERNAME_NOT_FOUND.getCode())
        .message(USERNAME_NOT_FOUND.getDefaultMassage())
        .build();
    return new ResponseEntity<>(response, NOT_FOUND);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(final EntityNotFoundException exception) {
    log.error(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(exception.getCause().getMessage())
        .message(exception.getMessage())
        .build();
    return new ResponseEntity<>(response, NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleException(
      final MethodArgumentNotValidException exception) {
    log.error(exception.getMessage(), exception);
    final List<ValidationError> errors = new ArrayList<>();
    exception.getBindingResult()
        .getAllErrors()
        .forEach(error -> {
          final String fieldName = ((FieldError) error).getField();
          final String errorCode = error.getDefaultMessage();
          errors.add(
              ValidationError.builder()
                  .field(fieldName)
                  .message(errorCode)
                  .build()
          );
        });
    final ErrorResponse errorResponse = ErrorResponse.builder()
        .validationErrorsList(errors)
        .build();
    return new ResponseEntity<>(errorResponse, BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
    log.error(exception.getMessage(), exception);
    final ErrorResponse response = ErrorResponse.builder()
        .code(INTERNAL_EXCEPTION.getCode())
        .message(INTERNAL_EXCEPTION.getDefaultMassage())
        .build();
    return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
  }
}