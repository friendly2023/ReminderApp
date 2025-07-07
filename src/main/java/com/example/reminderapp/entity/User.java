package com.example.reminderapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @Column(name = "username", nullable = false)
    @Getter
    @Setter
    private String userName;
    @Column(name = "email", nullable = false)
    @Getter
    @Setter
    private String email;
    @Column(name = "telegram_contact")
    @Getter
    @Setter
    private String telegramContact;
    @Column(name = "password", nullable = false)
    @Getter
    @Setter
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @Getter
    @Setter
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Reminder> reminders = new ArrayList<>();

    public User() {
        this.role = Role.USER;
    }

    public User(String userName, String email, String telegramContact, String password) {
        this.userName = userName;
        this.email = email;
        this.telegramContact = telegramContact;
        this.password = password;
        this.role = Role.USER;
    }
}