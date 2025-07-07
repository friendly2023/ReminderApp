package com.example.reminderapp.exception;

import java.util.List;

public record ValidationErrorResponse(
        List<Violation> violations
) {
}