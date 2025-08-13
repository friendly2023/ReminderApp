package com.example.reminderapp.validation;

import com.example.reminderapp.validation.impl.SortingParamValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SortingParamValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSortingParam {
    String message() default "Параметр должен быть из списка: name, date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
