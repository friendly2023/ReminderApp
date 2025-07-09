package com.example.reminderapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "reminder")
public class Reminder {
    @Id
    @Getter
    private long id;
    @Column(name = "title", nullable = false)
    @Getter
    @Setter
    private String title;
    @Column(name = "description")
    @Getter
    @Setter
    private String description;
    @Column(name = "remind", nullable = false)
    @Getter
    @Setter
    private ZonedDateTime remind;
    @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
    @Getter
    @Setter
    private long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Reminder() {
        this.userId = user.getId();
    }
}