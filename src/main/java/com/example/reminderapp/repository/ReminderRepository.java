package com.example.reminderapp.repository;

import com.example.reminderapp.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    Optional<Object> findByIdAndUserEmail(long idReminder, String email);

    Optional<Reminder> findFirstByUserEmailOrderByIdDesc(String email);

    List<Reminder> findByUserEmailOrderByTitleAsc(String email);

    List<Reminder> findByUserEmailOrderByTitleDesc(String email);

    List<Reminder> findByUserEmailOrderByRemindAsc(String email);

    List<Reminder> findByUserEmailOrderByRemindDesc(String email);

    @Query("SELECT r FROM Reminder r " +
            "WHERE r.user.email = :email " +
            "AND r.remind >= COALESCE(:from, r.remind) " +
            "AND r.remind <= COALESCE(:to, r.remind) " +
            "ORDER BY r.remind ASC")
    List<Reminder> findFilteredRemindersAsc(@Param("email") String email,
                                            @Param("from") ZonedDateTime from,
                                            @Param("to") ZonedDateTime to);

    @Query("SELECT r FROM Reminder r " +
            "WHERE r.user.email = :email " +
            "AND r.remind >= COALESCE(:from, r.remind) " +
            "AND r.remind <= COALESCE(:to, r.remind) " +
            "ORDER BY r.remind DESC")
    List<Reminder> findFilteredRemindersDESC(@Param("email") String email,
                                             @Param("from") ZonedDateTime from,
                                             @Param("to") ZonedDateTime to);
}