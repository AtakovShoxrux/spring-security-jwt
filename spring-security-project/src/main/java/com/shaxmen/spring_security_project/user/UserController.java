package com.shaxmen.spring_security_project.user;

import com.shaxmen.spring_security_project.user.dto.ChangePasswordRequestDto;
import com.shaxmen.spring_security_project.user.dto.ProfileUpdateRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User API")
public class UserController {

  private final UserService service;

  @PatchMapping("/me")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void updateProfileInfo(
      @RequestBody
      @Valid final ProfileUpdateRequestDto requestDto,
      Authentication authentication
  ) {
    this.service.updateProfileInfo(requestDto, getUserId(authentication));
  }

  @PostMapping("/me/password")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void changePassword(
      @RequestBody
      @Valid final ChangePasswordRequestDto requestDto,
      Authentication authentication
  ) {
    this.service.changePassword(requestDto, getUserId(authentication));
  }

  @PatchMapping("/me/deactivate")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deactivateAccount(final Authentication authentication) {
    this.service.deactivateAccount(getUserId(authentication));
  }

  @PatchMapping("/me/reactivate")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void reactivateAccount(final Authentication authentication) {
    this.service.reactivateAccount(getUserId(authentication));
  }

  @DeleteMapping("/me")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteAccount(final Authentication authentication) {
    this.service.deleteAccount(getUserId(authentication));
  }

  private String getUserId(final Authentication authentication) {
    return ((UserEntity) authentication.getPrincipal()).getId();
  }
}
// Should make some changes in this class for example separate classes like UserApi and UserController that implements UserApi