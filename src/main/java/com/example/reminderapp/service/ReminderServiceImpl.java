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

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;
    private final UserService userService;

    @Override
    public ReminderResponseDTO createReminder(NewReminderDTO newReminderDTO, String email) {
        log.debug("createReminder стартовал");

        log.debug("email: {}", email);
        log.debug("{}", newReminderDTO);

        User user = userService.getUserByEmail(email);

        Reminder newReminder = reminderMapper.toEntity(newReminderDTO);
        newReminder.setUser(user);
        ReminderResponseDTO responseDTO = reminderMapper.toReminderResponseDTO(newReminder);

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

        ReminderResponseDTO responseDTO = reminderMapper.toReminderResponseDTO(dbReminder);

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

    @Override
    public void deleteLastReminder(String email) {
        log.debug("deleteLastReminder стартовал: email={}", email);

        Reminder dbReminder = reminderRepository.findFirstByUserEmailOrderByIdDesc(email)
                .orElseThrow(() -> new EntityNotFoundException("У пользователя нет созданных напоминаний"));

        log.debug("idReminder = {}", dbReminder.getId());

        reminderRepository.delete(dbReminder);

        log.info("Последнее созданное напоминание удалено");
    }

    @Override
    public List<ReminderResponseDTO> getListSortReminder(String email, String sortBy, String direction) {
        log.debug("getListSortReminder стартовал: email={}, sortBy={}, direction={}", email, sortBy, direction);

        List<Reminder> reminders;

        String sort = sortBy.toLowerCase();
        String dir = direction.toLowerCase();

        switch (sort) {
            case "name" -> reminders = dir.equals("asc")
                    ? reminderRepository.findByUserEmailOrderByTitleAsc(email)
                    : reminderRepository.findByUserEmailOrderByTitleDesc(email);

            case "date" -> reminders = dir.equals("asc")
                    ? reminderRepository.findByUserEmailOrderByRemindAsc(email)
                    : reminderRepository.findByUserEmailOrderByRemindDesc(email);

            default -> throw new IllegalArgumentException("Неверный параметр сортировки: " + sortBy);
        }

        log.info("Получен отсортированный список напоминаний");

        return reminders.stream()
                .map(reminderMapper::toReminderResponseDTO)
                .toList();
    }

    private Reminder upReminder(Reminder dbReminder, NewReminderDTO newReminderDTO) {
        dbReminder.setTitle(newReminderDTO.title());
        dbReminder.setDescription(newReminderDTO.description());
        dbReminder.setRemind(newReminderDTO.remind());

        return dbReminder;
    }
}