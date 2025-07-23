package com.example.reminderapp.dto;

import java.time.ZonedDateTime;

public record ReminderResponseDTO(
        String title,
        String description,
        ZonedDateTime remind
) {
}