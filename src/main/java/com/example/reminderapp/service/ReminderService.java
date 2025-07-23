package com.example.reminderapp.service;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;
import com.example.reminderapp.entity.Reminder;

public interface ReminderService {

    ReminderResponseDTO createReminder(NewReminderDTO newReminderDTO, String email);

    Reminder getReminderById(long idReminder, String email);

    Reminder updateReminder(long idReminder, NewReminderDTO newReminderDTO, String email);
}