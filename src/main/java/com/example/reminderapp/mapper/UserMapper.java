package com.example.reminderapp.mapper;

import com.example.reminderapp.dto.UserRequestDto;
import com.example.reminderapp.dto.UserResponseDto;
import com.example.reminderapp.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toEntity(UserRequestDto dto) {

        return new User(
                dto.userName(),
                dto.email(),
                dto.telegramContact(),
                dto.password()
        );
    }

    public UserResponseDto toResponseDto(User user) {

        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getTelegramContact()
        );
    }
}