package com.example.reminderapp.validation.impl;

import com.example.reminderapp.validation.ValidIdReminder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class IdReminderValidator implements ConstraintValidator<ValidIdReminder, String> {

    private static final Pattern VALID_PATTERN = Pattern.compile("^(?:[1-8]\\d{0,18})$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && VALID_PATTERN.matcher(value).matches();
    }
}
