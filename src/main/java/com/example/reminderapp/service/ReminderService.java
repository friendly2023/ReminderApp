package com.example.reminderapp.service;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface ReminderService {

    ReminderResponseDTO createReminder(NewReminderDTO newReminderDTO, OAuth2AuthenticationToken auth);
}