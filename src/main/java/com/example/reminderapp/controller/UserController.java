package com.example.reminderapp.controller;

import com.example.reminderapp.dto.UserRequestDto;
import com.example.reminderapp.dto.UserResponseDto;
import com.example.reminderapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/api/v1/user")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UserController {

}