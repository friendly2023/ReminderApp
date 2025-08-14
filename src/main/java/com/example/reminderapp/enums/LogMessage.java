package com.example.reminderapp.enums;

public enum LogMessage {
    REQUEST_PREFIX("Получен запрос на "),
    SUCCESS_PREFIX("Выполнен запрос на ");

    private final String text;

    LogMessage(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}