package com.example.reminderapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;

public record NewReminderDTO(
        @NotBlank(message = "Заголовок не может быть пустым")
        @Size(max = 255, message = "Заголовок должен содержать не более 255 символов")
        String title,
        @Size(max = 4096, message = "Описание должно содержать не более 4096 символов")
        String description,
        @NotNull(message = "Дата и время напоминания обязательны")
        ZonedDateTime remind
) {
}