package com.example.reminderapp.enums;

public enum FilterParam {
    DATE("date");
    private final String text;

    FilterParam(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
