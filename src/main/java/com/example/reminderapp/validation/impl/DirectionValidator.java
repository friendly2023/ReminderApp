package com.example.reminderapp.validation.impl;

import com.example.reminderapp.validation.ValidDirection;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class DirectionValidator implements ConstraintValidator<ValidDirection, String> {

    private static final Set<String> ALLOWED_VALUES = Set.of("asc", "desc");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && ALLOWED_VALUES.contains(value);
    }
}
