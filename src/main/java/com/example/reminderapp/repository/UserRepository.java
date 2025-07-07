package com.example.reminderapp.repository;

import com.example.reminderapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
