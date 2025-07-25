package com.example.reminderapp.controller;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;
import com.example.reminderapp.entity.Reminder;
import com.example.reminderapp.mapper.ReminderMapper;
import com.example.reminderapp.service.ReminderService;
import com.example.reminderapp.validation.ValidIdReminder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/api/v1/reminder")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ReminderController {

    private static final String LOG_REQUEST_PREFIX = "Получен запрос на ";
    private static final String LOG_SUCCESS_PREFIX = "Выполнен запрос на ";
    private static final String CREATE_REMINDER = "создание напоминания";
    private static final String GET_REMINDER = "получение напоминания по id";
    private static final String UPDATE_REMINDER = "изменение напоминания";
    private static final String DELETE_REMINDER_BY_ID = "удаление напоминания по id";
    private final ReminderService reminderService;
    private final ReminderMapper mapper;

    @PostMapping(value = "/create")
    public ResponseEntity<ReminderResponseDTO> createReminder(@Valid @RequestBody NewReminderDTO newReminderDTO, OAuth2AuthenticationToken auth) {
        log.info(LOG_REQUEST_PREFIX + CREATE_REMINDER);

        String email = auth.getPrincipal().getAttribute("email");
        ReminderResponseDTO createdReminder = reminderService.createReminder(newReminderDTO, email);

        log.info(LOG_SUCCESS_PREFIX + CREATE_REMINDER);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdReminder);
    }

    @GetMapping(value = "/{idReminder}")
    public ResponseEntity<ReminderResponseDTO> getReminder(@PathVariable @ValidIdReminder String idReminder,
                                                           OAuth2AuthenticationToken auth) {
        log.info(LOG_REQUEST_PREFIX + GET_REMINDER);

        String email = auth.getPrincipal().getAttribute("email");
        Reminder dbReminder = reminderService.getReminderById(Long.parseLong(idReminder), email);
        ReminderResponseDTO responseDTO = mapper.toReminderResponseDTO(dbReminder);

        log.info(LOG_SUCCESS_PREFIX + GET_REMINDER);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PutMapping(value = "/{idReminder}")
    public ResponseEntity<ReminderResponseDTO> updateReminder(@PathVariable @ValidIdReminder String idReminder,
                                                              @Valid @RequestBody NewReminderDTO updateReminderDTO,
                                                              OAuth2AuthenticationToken auth) {
        log.info(LOG_REQUEST_PREFIX + UPDATE_REMINDER);

        String email = auth.getPrincipal().getAttribute("email");
        Reminder updatedReminder = reminderService.updateReminder(Long.parseLong(idReminder), updateReminderDTO, email);
        ReminderResponseDTO responseDTO = mapper.toReminderResponseDTO(updatedReminder);

        log.info(LOG_SUCCESS_PREFIX + UPDATE_REMINDER);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping(value = "/{idReminder}")
    public ResponseEntity<Void> deleteReminder(@PathVariable @ValidIdReminder String idReminder,
                                               OAuth2AuthenticationToken auth) {
        log.info(LOG_REQUEST_PREFIX + DELETE_REMINDER_BY_ID);

        log.info(LOG_SUCCESS_PREFIX + DELETE_REMINDER_BY_ID);

        return ResponseEntity.noContent().build();
    }
}