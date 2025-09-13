package com.shaxmen.spring_security_project.auth;

import com.shaxmen.spring_security_project.auth.dto.AuthenticationRequestDto;
import com.shaxmen.spring_security_project.auth.dto.AuthenticationResponseDto;
import com.shaxmen.spring_security_project.auth.dto.RefreshTokenRequestDto;
import com.shaxmen.spring_security_project.auth.dto.RegistrationRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponseDto> login(
      @Valid
      @RequestBody
      AuthenticationRequestDto requestDto
  ) {
    return ResponseEntity.ok(authenticationService.login(requestDto));
  }

  @PostMapping("/register")
  public ResponseEntity<Void> register(
      @Valid
      @RequestBody
      RegistrationRequestDto requestDto
  ) {
    authenticationService.register(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/refresh")
  public ResponseEntity<AuthenticationResponseDto> refresh(
      @RequestBody
      RefreshTokenRequestDto requestDto
  ) {
    return ResponseEntity.ok(authenticationService.refresh(requestDto));
  }
}
