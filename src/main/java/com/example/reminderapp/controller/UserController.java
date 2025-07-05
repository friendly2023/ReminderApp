package com.example.reminderapp.controller;

import com.example.reminderapp.dto.UserRequestDto;
import com.example.reminderapp.dto.UserResponseDto;
import com.example.reminderapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/api/v1/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {

        log.info("Получен запрос на создание пользователя");
        log.debug("Запрос: {}", userRequestDto);

        UserResponseDto createdUser = userService.createUser(userRequestDto);

        ResponseEntity<UserResponseDto> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(createdUser);

        log.info("Выполнен запрос на создание пользователя");

        return response;
    }
}