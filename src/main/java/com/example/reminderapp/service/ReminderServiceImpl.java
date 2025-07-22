package com.example.reminderapp.service;

import com.example.reminderapp.dto.NewReminderDTO;
import com.example.reminderapp.entity.Reminder;
import com.example.reminderapp.entity.User;
import com.example.reminderapp.repository.ReminderRepository;
import com.example.reminderapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final UserRepository userRepository;
    private final ReminderRepository reminderRepository;

    @Override
    public Reminder createReminder(NewReminderDTO newReminderDTO, OAuth2AuthenticationToken auth) {
        log.debug("ReminderServiceImpl стартовал");

        String email = auth.getPrincipal().getAttribute("email");

        log.debug("email: {}", email);
        log.debug("{}", newReminderDTO);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с email " + email + " не найден"));

        Reminder newReminder = new Reminder();
        newReminder.setTitle(newReminderDTO.title());
        newReminder.setDescription(newReminderDTO.description());
        newReminder.setRemind(newReminderDTO.remind());
        newReminder.setUser(user);

        reminderRepository.save(newReminder);

        log.info("Напоминание создано и сохранено в БД");

        return newReminder;
    }
}