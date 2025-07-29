package com.example.reminderapp.repository;

import com.example.reminderapp.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    Optional<Object> findByIdAndUserEmail(long idReminder, String email);

    Optional<Reminder> findFirstByUserEmailOrderByIdDesc(String email);

}