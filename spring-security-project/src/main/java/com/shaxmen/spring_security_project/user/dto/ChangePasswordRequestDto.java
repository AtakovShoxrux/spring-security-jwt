package com.shaxmen.spring_security_project.user.dto;

import lombok.Builder;

@Builder
public class ChangePasswordRequestDto {

  private String currentPassword;

  private String newPassword;

  private String confirmNewPassword;

  public ChangePasswordRequestDto() {
  }

  public ChangePasswordRequestDto(
      String currentPassword,
      String newPassword,
      String confirmNewPassword
  ) {
    this.currentPassword = currentPassword;
    this.newPassword = newPassword;
    this.confirmNewPassword = confirmNewPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public void setConfirmNewPassword(String confirmNewPassword) {
    this.confirmNewPassword = confirmNewPassword;
  }

  public String getCurrentPassword() {
    return currentPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public String getConfirmNewPassword() {
    return confirmNewPassword;
  }
}
