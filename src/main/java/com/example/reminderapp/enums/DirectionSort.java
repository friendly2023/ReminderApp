package com.example.reminderapp.enums;

public enum DirectionSort {
    ASC("asc"),
    DESC("desc");

    private final String text;

    DirectionSort(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}