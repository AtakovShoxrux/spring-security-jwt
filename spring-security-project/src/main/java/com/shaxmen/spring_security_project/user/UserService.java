package com.shaxmen.spring_security_project.user;

import com.shaxmen.spring_security_project.user.dto.ChangePasswordRequestDto;
import com.shaxmen.spring_security_project.user.dto.ProfileUpdateRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  void updateProfileInfo(ProfileUpdateRequestDto requestDto, String userId);

  void changePassword(ChangePasswordRequestDto requestDto, String userId);

  void deactivateAccount(String userId);

  void reactivateAccount(String userId);

  void deleteAccount(String userId);
}