package com.example.reminderapp.dto;

import java.time.ZonedDateTime;

public record NewReminderDTO(
        String title,
        String description,
        ZonedDateTime remind
) {
}