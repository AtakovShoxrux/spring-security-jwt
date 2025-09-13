package com.shaxmen.spring_security_project.auth;

import com.shaxmen.spring_security_project.auth.dto.AuthenticationRequestDto;
import com.shaxmen.spring_security_project.auth.dto.AuthenticationResponseDto;
import com.shaxmen.spring_security_project.auth.dto.RefreshTokenRequestDto;
import com.shaxmen.spring_security_project.auth.dto.RegistrationRequestDto;
import com.shaxmen.spring_security_project.exception.BusinessException;
import com.shaxmen.spring_security_project.exception.ErrorCode;
import com.shaxmen.spring_security_project.role.RoleEntity;
import com.shaxmen.spring_security_project.role.RoleRepository;
import com.shaxmen.spring_security_project.security.JwtService;
import com.shaxmen.spring_security_project.user.UserEntity;
import com.shaxmen.spring_security_project.user.UserMapper;
import com.shaxmen.spring_security_project.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserMapper userMapper;

  @Override
  public AuthenticationResponseDto login(AuthenticationRequestDto requestDto) {
    final Authentication auth = this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            requestDto.getEmail(),
            requestDto.getPassword()
        )
    );

    final UserEntity user = (UserEntity) auth.getPrincipal();
    final String accessToken = this.jwtService.generateAccessToken(user.getUsername());
    final String refreshToken = this.jwtService.generateRefreshToken(user.getUsername());
    final String tokenType = "Bearer";
    return AuthenticationResponseDto
        .builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .tokenType(tokenType)
        .build();
  }

  @Override
  @Transactional
  public void register(RegistrationRequestDto requestDto) {
    checkUserEmail(requestDto.getEmail());
    checkUserPhoneNumber(requestDto.getPhoneNumber());
    checkUserPasswords(requestDto.getPassword(), requestDto.getConfirmPassword());

    final RoleEntity userRole = this.roleRepository.findByName("ROLE_USER")
        .orElseThrow(
            () -> new EntityNotFoundException("Role user does not exist")
        );
    final List<RoleEntity> roles = new ArrayList<>();
    roles.add(userRole);

    final UserEntity user = this.userMapper.toUser(requestDto);
    user.setRoles(roles);
    log.debug("Saving user {}", user.getUsername());
    this.userRepository.save(user);

    final List<UserEntity> users = new ArrayList<>();
    users.add(user);
    userRole.setUsers(users);
    this.roleRepository.save(userRole);
  }

  @Override
  public AuthenticationResponseDto refresh(RefreshTokenRequestDto requestDto) {
    final String newAccessToken = this.jwtService.generateAccessToken(requestDto.getRefreshToken());
    final String tokenType = "Bearer";
    return AuthenticationResponseDto
        .builder()
        .accessToken(newAccessToken)
        .refreshToken(requestDto.getRefreshToken())
        .tokenType(tokenType)
        .build();
  }

  private void checkUserEmail(String email) {
    final Boolean emailExists = this.userRepository.existsByEmailIgnoreCase(email);
    if (emailExists) {
      throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
  }

  private void checkUserPhoneNumber(String phoneNumber) {
    final Boolean phoneNumberExists = this.userRepository.existsByPhoneNumber(phoneNumber);
    if (phoneNumberExists) {
      throw new BusinessException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
    }
  }

  private void checkUserPasswords(String password, String confirmPassword) {
    if (!password.equals(confirmPassword)) {
      throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
    }
  }
}
