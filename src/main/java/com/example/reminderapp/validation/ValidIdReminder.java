package com.example.reminderapp.validation;

import com.example.reminderapp.validation.impl.IdReminderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdReminderValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIdReminder {
    String message() default "ID должен быть положительным числом менее 9*10^18";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
