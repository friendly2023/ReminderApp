package com.example.reminderapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;

@Entity
@Table(name = "reminder")
public class Reminder {
    @Id
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "remind", nullable = false)
    private ZonedDateTime remind;
    @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Reminder() {
        this.userId = user.getId();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getRemind() {
        return remind;
    }

    public void setRemind(ZonedDateTime time) {
        this.remind = time;
    }

    public int getUserId() {
        return user != null ? user.getId() : userId;
    }
}