package com.example.reminderapp.controller;

import com.example.reminderapp.dto.ReminderResponseDTO;
import com.example.reminderapp.service.ReminderService;
import com.example.reminderapp.validation.ValidDirection;
import com.example.reminderapp.validation.ValidSortingParam;
import enums.LogMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domain/api/v1")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ReminderListController {
    private static final String GET_SORT_REMINDER = "получение отсортированого списка напоминаний";

    private final ReminderService reminderService;

    /**
     * GET /sort?by={name|date}&direction={asc|desc}
     *
     * @param sortBy    поле для сортировки: name или date
     * @param direction порядок сортировки: asc или desc
     */
    @GetMapping(value = "/sort")
    public ResponseEntity<List<ReminderResponseDTO>> getSortReminders(@RequestParam("by") @ValidSortingParam String sortBy,
                                                                      @RequestParam("direction") @ValidDirection String direction,
                                                                      OAuth2AuthenticationToken auth) {
        log.info("{}{}", LogMessage.REQUEST_PREFIX.text(), GET_SORT_REMINDER);
        System.out.println(direction);

        List<ReminderResponseDTO> listSortReminders = reminderService.getListSortReminder(getEmailFromToken(auth), sortBy, direction);

        log.info("{}{}", LogMessage.SUCCESS_PREFIX.text(), GET_SORT_REMINDER);

        return ResponseEntity.ok(listSortReminders);
    }

    private String getEmailFromToken(OAuth2AuthenticationToken auth) {

        return auth.getPrincipal().getAttribute("email");
    }
}
