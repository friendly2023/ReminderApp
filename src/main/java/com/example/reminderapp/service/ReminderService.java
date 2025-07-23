package com.example.reminderapp.service;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;

public interface ReminderService {

    ReminderResponseDTO createReminder(NewReminderDTO newReminderDTO, String email);

    ReminderResponseDTO getReminderById(long idReminder, String email);
}