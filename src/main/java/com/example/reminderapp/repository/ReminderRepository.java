package com.example.reminderapp.repository;

import com.example.reminderapp.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    Optional<Object> findByIdAndUserEmail(long idReminder, String email);

    Optional<Reminder> findFirstByUserEmailOrderByIdDesc(String email);

    List<Reminder> findByUserEmailOrderByTitleAsc(String email);

    List<Reminder> findByUserEmailOrderByTitleDesc(String email);

    List<Reminder> findByUserEmailOrderByRemindAsc(String email);

    List<Reminder> findByUserEmailOrderByRemindDesc(String email);
}