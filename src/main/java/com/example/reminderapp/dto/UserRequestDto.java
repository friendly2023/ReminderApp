package com.example.reminderapp.dto;

public record UserRequestDto(
        String userName,
        String email,
        String telegramContact,
        String password
) {
}