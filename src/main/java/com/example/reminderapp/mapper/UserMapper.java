package com.example.reminderapp.mapper;

import com.example.reminderapp.dto.UserRequestDto;
import com.example.reminderapp.dto.UserResponseDto;
import com.example.reminderapp.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toEntity(UserRequestDto dto) {

        return new User(
                dto.userName(),
                dto.email(),
                dto.telegramContact(),
                passwordEncoder.encode(dto.password())
        );
    }

    public UserResponseDto toResponseDto(User user) {

        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getTelegramContact(),
                user.getRole()
        );
    }
}