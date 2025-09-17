package com.shaxmen.spring_security_project.user;

import com.shaxmen.spring_security_project.exception.BusinessException;
import com.shaxmen.spring_security_project.exception.ErrorCode;
import com.shaxmen.spring_security_project.user.dto.ChangePasswordRequestDto;
import com.shaxmen.spring_security_project.user.dto.ProfileUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findByEmailIgnoreCase(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("User not found with this username: " + username));
  }

  @Override
  public void updateProfileInfo(ProfileUpdateRequestDto requestDto, String userId) {
    var savedUser = this.userRepository.findById(userId)
        .orElseThrow(
            () -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId)
        );
    this.userMapper.mergeUserInfo(savedUser, requestDto);
    this.userRepository.save(savedUser);
  }

  @Override
  public void changePassword(ChangePasswordRequestDto requestDto, String userId) {
    if (!requestDto.getNewPassword().equals(requestDto.getConfirmNewPassword())) {
      throw new BusinessException(ErrorCode.CHANGE_PASSWORD_MISMATCH);
    }

    final UserEntity savedUser = this.userRepository.findById(userId)
        .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));

    if (!this.passwordEncoder.matches(requestDto.getCurrentPassword(), savedUser.getPassword())) {
      throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
    }

    final String encodedPassword = this.passwordEncoder.encode(requestDto.getNewPassword());
    savedUser.setPassword(encodedPassword);
    this.userRepository.save(savedUser);
  }

  @Override
  public void deactivateAccount(String userId) {
    final UserEntity savedUser = this.userRepository.findById(userId)
        .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));

    if (!savedUser.isEnabled()) {
      throw new BusinessException(ErrorCode.ACCOUNT_ALREADY_DEACTIVATED);
    }

    savedUser.setEnabled(false);
    this.userRepository.save(savedUser);
  }

  @Override
  public void reactivateAccount(String userId) {
    final UserEntity savedUser = this.userRepository.findById(userId)
        .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));

    if (savedUser.isEnabled()) {
      throw new BusinessException(ErrorCode.ACCOUNT_ALREADY_ACTIVATED);
    }

    savedUser.setEnabled(true);
    this.userRepository.save(savedUser);
  }

  @Override
  public void deleteAccount(String userId) {

  }
}
