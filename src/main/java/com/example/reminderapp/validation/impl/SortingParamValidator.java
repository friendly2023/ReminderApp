package com.example.reminderapp.validation.impl;

import com.example.reminderapp.validation.ValidSortingParam;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class SortingParamValidator implements ConstraintValidator<ValidSortingParam, String> {

    private static final Set<String> ALLOWED_VALUES = Set.of("name", "date");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && ALLOWED_VALUES.contains(value);
    }
}
