package com.example.reminderapp.service.impl;

import com.example.reminderapp.dto.UserRequestDto;
import com.example.reminderapp.dto.UserResponseDto;
import com.example.reminderapp.repository.UserRepository;
import com.example.reminderapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return null;
    }

//    @Override
//    public UserResponseDto createUser(UserRequestDto userRequestDto) {
//        log.debug("Начат процесс создания пользователя");
//
//        validateUniqueUserFields(userRequestDto);
//
//        log.debug("Валидация при создании User прошла успешно");
//
//        User user = userMapper.toEntity(userRequestDto);
//        userRepository.save(user);
//
//        log.info("Пользователь создан");
//
//        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
//
//        log.debug("Пользователь: {}", userResponseDto);
//
//        return userResponseDto;
//    }
//
//    private void validateUniqueUserFields(UserRequestDto userRequestDto) {
//
//        List<String> errors = new ArrayList<>();
//
//        if (userRepository.existsByUserName(userRequestDto.userName())) {
//            errors.add("Пользователь с таким username уже существует");
//        }
//        if (userRepository.existsByEmail(userRequestDto.email())) {
//            errors.add("Пользователь с таким email уже существует");
//        }
//
//        if (!errors.isEmpty()) {
//            throw new RegistrationValidationException(errors);
//        }
//    }
}