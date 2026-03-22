package com.example.reminderapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TelegramMessageRequestDTO {

    @JsonProperty("date")
    private LocalDateTime dateTime;

    private String from;

    private String text;

    @JsonProperty("text")
    public void setText(Object text) {
        if (text instanceof String) {
            this.text = ((String) text).toLowerCase();
        }
        // Если не строка - оставляем null (будет пропущено)
    }

    /**
     * Проверяет, валидно ли сообщение для обработки
     */
    public boolean isValidForAnalysis() {
        return dateTime != null &&
                from != null && !from.trim().isEmpty() &&
                text != null && !text.trim().isEmpty();
    }

    public String getText() {
        return text != null ? text : "";
    }
}