package com.example.reminderapp.dto;

public record UserResponseDto(
        int id,
        String userName,
        String email,
        String telegramContact
) {
}