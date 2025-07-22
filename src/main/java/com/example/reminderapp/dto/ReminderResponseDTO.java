package com.example.reminderapp.dto;

import java.time.ZonedDateTime;

public record ReminderResponseDTO(
        Long id,
        String title,
        String description,
        ZonedDateTime remind
) {
}