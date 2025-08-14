package com.example.reminderapp.enums;

public enum SortingParam {
    NAME("name"),
    DATE("date");

    private final String text;

    SortingParam(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
