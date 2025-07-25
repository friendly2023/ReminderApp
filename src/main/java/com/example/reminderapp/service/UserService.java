package com.example.reminderapp.service;

import com.example.reminderapp.entity.User;

public interface UserService {
    User getUserByEmail(String email);
}