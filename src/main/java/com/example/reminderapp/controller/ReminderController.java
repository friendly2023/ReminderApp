package com.example.reminderapp.controller;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;
import com.example.reminderapp.entity.Reminder;
import com.example.reminderapp.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping(value = "/create")
    public ResponseEntity<ReminderResponseDTO> createReminder(@Valid @RequestBody NewReminderDTO newReminderDTO, OAuth2AuthenticationToken auth) {
        log.info("Получен запрос на создание напоминания");

        Reminder createdReminder = reminderService.createReminder(newReminderDTO, auth);

        ReminderResponseDTO responseDTO = new ReminderResponseDTO(
                createdReminder.getId(),
                createdReminder.getTitle(),
                createdReminder.getDescription(),
                createdReminder.getRemind()
        );

        log.info("Выполнен запрос на создание напоминания");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}