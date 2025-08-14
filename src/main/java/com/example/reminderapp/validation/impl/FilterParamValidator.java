package com.example.reminderapp.validation.impl;

import com.example.reminderapp.enums.FilterParam;
import com.example.reminderapp.validation.ValidFilterParam;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class FilterParamValidator implements ConstraintValidator<ValidFilterParam, String> {

    private static final Set<String> ALLOWED_VALUES = Set.of(
            FilterParam.DATE.text().toLowerCase()
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && ALLOWED_VALUES.contains(value.toLowerCase());
    }
}
