package com.example.reminderapp.service.impl;

import com.example.reminderapp.dto.UserRequestDto;
import com.example.reminderapp.dto.UserResponseDto;
import com.example.reminderapp.entity.User;
import com.example.reminderapp.mapper.UserMapper;
import com.example.reminderapp.repository.UserRepository;
import com.example.reminderapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        log.debug("Начат процесс создания пользователя");

        User user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);

        log.info("Пользователь создан");

        UserResponseDto userResponseDto = userMapper.toResponseDto(user);

        log.debug("Пользователь: {}", userResponseDto);

        return userResponseDto;
    }
}