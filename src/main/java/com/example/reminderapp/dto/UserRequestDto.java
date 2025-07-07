package com.example.reminderapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank
        @Size(min = 2, max = 255)
        String userName,
        @NotBlank
        @Email
        String email,
        @Pattern(regexp = "^@\\w{3,32}$")
        String telegramContact,
        @NotBlank
        @Size(min = 8, max = 255)
        String password
) {
}