package com.example.reminderapp.controller;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;
import com.example.reminderapp.mapper.ReminderMapper;
import com.example.reminderapp.service.ReminderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/api/v1/reminder")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ReminderController {

    private final ReminderService reminderService;
    private final ReminderMapper mapper;

    @PostMapping(value = "/create")
    public ResponseEntity<ReminderResponseDTO> createReminder(@Valid @RequestBody NewReminderDTO newReminderDTO, OAuth2AuthenticationToken auth) {
        log.info("Получен запрос на создание напоминания");

        String email = auth.getPrincipal().getAttribute("email");
        ReminderResponseDTO createdReminder = reminderService.createReminder(newReminderDTO, email);

        log.info("Выполнен запрос на создание напоминания");

        return ResponseEntity.status(HttpStatus.CREATED).body(createdReminder);
    }

    @GetMapping(value = "/{idReminder}")
    public ResponseEntity<ReminderResponseDTO> getReminder(@PathVariable @Pattern(
            regexp = "^(?:[1-8]\\d{0,18})$",
            message = "ID должен быть положительным числом менее 9*10^18")
                                                           String idReminder,
                                                           OAuth2AuthenticationToken auth) {
        log.info("Получен запрос на получение напоминания по id");

        String email = auth.getPrincipal().getAttribute("email");
        ReminderResponseDTO dbReminder = reminderService.getReminderById(Long.parseLong(idReminder), email);

        log.info("Выполнен запрос на получение напоминания по id");

        return ResponseEntity.status(HttpStatus.OK).body(dbReminder);
    }

}