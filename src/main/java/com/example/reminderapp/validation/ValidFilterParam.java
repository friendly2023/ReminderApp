package com.example.reminderapp.validation;

import com.example.reminderapp.validation.impl.FilterParamValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FilterParamValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFilterParam {
    String message() default "Параметр должен быть из списка: date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
