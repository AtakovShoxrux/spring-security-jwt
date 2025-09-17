package com.shaxmen.spring_security_project.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;

public class EmailDomainValidation implements ConstraintValidator<IsValidEmail, String> {

  private final Set<String> blocked;

  public EmailDomainValidation(
      @Value("${security.invalid-emails}") final List<String> listOfEmails
  ) {
    this.blocked = listOfEmails
        .stream()
        .map(String::toLowerCase)
        .collect(Collectors.toSet());
  }

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (email == null || !email.contains("@")) {
      return true;
    }
    final String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
    return !this.blocked.contains(domain);
  }
}
