package com.example.reminderapp.service;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.dto.ReminderResponseDTO;
import com.example.reminderapp.entity.Reminder;
import com.example.reminderapp.entity.User;
import com.example.reminderapp.enums.DirectionSort;
import com.example.reminderapp.enums.SortingParam;
import com.example.reminderapp.mapper.ReminderMapper;
import com.example.reminderapp.repository.ReminderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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

        SortingParam sortParam;
        DirectionSort dirParam;

        try {
            sortParam = SortingParam.valueOf(sortBy.toUpperCase());
            dirParam = DirectionSort.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверный параметр сортировки или направления: "
                    + sortBy + ", " + direction);
        }

        List<Reminder> reminders;

        switch (sortParam) {
            case NAME -> reminders = dirParam == DirectionSort.ASC
                    ? reminderRepository.findByUserEmailOrderByTitleAsc(email)
                    : reminderRepository.findByUserEmailOrderByTitleDesc(email);

            case DATE -> reminders = dirParam == DirectionSort.ASC
                    ? reminderRepository.findByUserEmailOrderByRemindAsc(email)
                    : reminderRepository.findByUserEmailOrderByRemindDesc(email);

            default -> throw new IllegalArgumentException("Неверный параметр сортировки: " + sortBy);
        }

        log.info("Получен отсортированный список напоминаний");

        return reminders.stream()
                .map(reminderMapper::toReminderResponseDTO)
                .toList();
    }

    @Override
    public List<ReminderResponseDTO> getListFilterRemindersByDate(String email,
                                                                  ZonedDateTime from,
                                                                  ZonedDateTime to,
                                                                  String direction) {

        log.debug("getListFilterReminders стартовал: email={}, from={}, to={}, direction={}",
                email, from, to, direction);

        DirectionSort directionSort;
        directionSort = DirectionSort.valueOf(direction.toUpperCase());

        List<Reminder> reminders;

        switch (directionSort) {
            case ASC -> reminders = reminderRepository.findFilteredRemindersAsc(email,
                    from,
                    to);

            case DESC -> reminders = reminderRepository.findFilteredRemindersDESC(email,
                    from,
                    to);

            default -> throw new IllegalArgumentException("Неверный параметр сортировки: " + directionSort);
        }

        log.info("Получен отфильтрованный список напоминаний");

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