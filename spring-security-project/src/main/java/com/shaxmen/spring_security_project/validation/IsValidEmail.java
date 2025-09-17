package com.shaxmen.spring_security_project.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailDomainValidation.class)
public @interface IsValidEmail {

  String message() default "Invalid emails are not allowed";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
