package com.shaxmen.spring_security_project.user;

import com.shaxmen.spring_security_project.auth.dto.RegistrationRequestDto;
import com.shaxmen.spring_security_project.user.dto.ProfileUpdateRequestDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "firstName", source = "firstName")
  @Mapping(target = "lastName", source = "lastName")
  @Mapping(target = "dateOfBirth", source = "dateOfBirth")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void mergeUserInfo(@MappingTarget UserEntity savedUser, ProfileUpdateRequestDto requestDto);

  @Mapping(target = "firstName", source = "firstName")
  @Mapping(target = "lastName", source = "lastName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "phoneNumber", source = "phoneNumber")
  @Mapping(target = "password", source = "password")
  @Mapping(target = "enabled", constant = "true") // This is a default version of the user if you want to do some verifying before it you should set it to false (Phone verifying or Email one)
  @Mapping(target = "locked", constant = "false")
  @Mapping(target = "credentialsExpired", constant = "false")
  @Mapping(target = "emailVerified", constant = "false")
  @Mapping(target = "phoneNumberVerified", constant = "false")
  UserEntity toUser(RegistrationRequestDto requestDto);
}