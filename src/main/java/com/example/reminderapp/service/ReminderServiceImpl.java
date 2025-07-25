package com.example.reminderapp.service;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;
import com.example.reminderapp.entity.Reminder;
import com.example.reminderapp.entity.User;
import com.example.reminderapp.mapper.ReminderMapper;
import com.example.reminderapp.repository.ReminderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper mapper;
    private final UserService userService;

    @Override
    public ReminderResponseDTO createReminder(NewReminderDTO newReminderDTO, String email) {
        log.debug("createReminder стартовал");

        log.debug("email: {}", email);
        log.debug("{}", newReminderDTO);

        User user = userService.getUserByEmail(email);

        Reminder newReminder = mapper.toEntity(newReminderDTO);
        newReminder.setUser(user);
        ReminderResponseDTO responseDTO = mapper.toReminderResponseDTO(newReminder);

        reminderRepository.save(newReminder);

        log.info("Напоминание создано и сохранено в БД");

        return responseDTO;
    }

    @Override
    public Reminder getReminderById(long idReminder, String email) {
        log.debug("getReminderById стартовал");
        log.debug("idReminder: {}, email: {}", idReminder, email);

        Reminder dbReminder = (Reminder) reminderRepository.findByIdAndUserEmail(idReminder, email)
                .orElseThrow(() -> new EntityNotFoundException("Напоминание не найдено или не принадлежит пользователю"));

        ReminderResponseDTO responseDTO = mapper.toReminderResponseDTO(dbReminder);

        log.info("Напоминание найдено");

        return dbReminder;
    }

    @Override
    public Reminder updateReminder(long idReminder, NewReminderDTO newReminderDTO, String email) {
        log.debug("updateReminder стартовал");
        log.debug("idReminder: {}, email: {}", idReminder, email);

        Reminder dbReminder = getReminderById(idReminder, email);
        Reminder updatedReminder = upReminder(dbReminder, newReminderDTO);

        reminderRepository.save(updatedReminder);

        log.info("Напоминание отредактированно");

        return updatedReminder;
    }

    @Override
    public void deleteReminderById(long idReminder, String email) {
        log.debug("deleteReminderById стартовал: idReminder={}, email={}", idReminder, email);

        Reminder reminder = getReminderById(idReminder, email);
        reminderRepository.delete(reminder);

        log.info("Напоминание по id удалено");
    }

    private Reminder upReminder(Reminder dbReminder, NewReminderDTO newReminderDTO) {
        dbReminder.setTitle(newReminderDTO.title());
        dbReminder.setDescription(newReminderDTO.description());
        dbReminder.setRemind(newReminderDTO.remind());

        return dbReminder;
    }
}