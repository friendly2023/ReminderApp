package com.example.reminderapp.dto;

import com.example.reminderapp.entity.Role;

public record UserResponseDto(
        int id,
        String userName,
        String email,
        String telegramContact,
        Role role
) {
}