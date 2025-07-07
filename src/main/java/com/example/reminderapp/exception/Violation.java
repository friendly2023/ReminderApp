package com.example.reminderapp.exception;

public record Violation(
        String fieldName,
        String message
) {
}