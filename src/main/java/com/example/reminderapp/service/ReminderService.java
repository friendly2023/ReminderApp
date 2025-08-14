package com.example.reminderapp.service;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;
import com.example.reminderapp.entity.Reminder;

import java.time.ZonedDateTime;
import java.util.List;

public interface ReminderService {

    ReminderResponseDTO createReminder(NewReminderDTO newReminderDTO, String email);

    Reminder getReminderById(long idReminder, String email);

    Reminder updateReminder(long idReminder, NewReminderDTO newReminderDTO, String email);

    void deleteReminderById(long idReminder, String email);

    void deleteLastReminder(String email);

    List<ReminderResponseDTO> getListSortReminder(String email, String sortBy, String direction);

    List<ReminderResponseDTO> getListFilterRemindersByDate(String email, ZonedDateTime from, ZonedDateTime to, String direction);
}