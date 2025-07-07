package com.example.reminderapp.exception;

import java.util.List;

public class RegistrationValidationException extends RuntimeException {

    private final List<String> errors;

    public RegistrationValidationException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}