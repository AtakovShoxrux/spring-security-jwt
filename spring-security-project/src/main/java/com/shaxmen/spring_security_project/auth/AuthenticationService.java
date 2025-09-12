package com.shaxmen.spring_security_project.auth;

import com.shaxmen.spring_security_project.auth.dto.AuthenticationRequestDto;
import com.shaxmen.spring_security_project.auth.dto.AuthenticationResponseDto;
import com.shaxmen.spring_security_project.auth.dto.RefreshTokenRequestDto;
import com.shaxmen.spring_security_project.auth.dto.RegistrationRequestDto;

public interface AuthenticationService {

  AuthenticationResponseDto login(AuthenticationRequestDto requestDto);

  void register(RegistrationRequestDto requestDto);

  AuthenticationResponseDto refresh(RefreshTokenRequestDto requestDto);
}
