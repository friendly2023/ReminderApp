package com.example.reminderapp.service;

import com.example.reminderapp.dto.TelegramMessageRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SimpleAnalyzer {

    public List<Map<String, Object>> getSimpleStats(
            List<TelegramMessageRequestDTO> messages,
            String searchWord) {

        String word = searchWord.toLowerCase();

        return messages.stream()
                .filter(TelegramMessageRequestDTO::isValidForAnalysis)
                .collect(Collectors.groupingBy(
                        TelegramMessageRequestDTO::getFrom,
                        Collectors.toList()
                ))
                .entrySet().stream()
                .map(entry -> {
                    String user = entry.getKey();
                    List<TelegramMessageRequestDTO> userMessages = entry.getValue();

                    // ИЗМЕНИТЬ ТУТ: используем TreeMap для автоматической сортировки
                    Map<String, Long> monthlyStats = new TreeMap<>();

                    for (TelegramMessageRequestDTO msg : userMessages) {
                        long count = countSeparateYa(msg.getText());
                        if (count > 0) {
                            LocalDateTime dt = msg.getDateTime();
                            String monthKey = dt.getYear() + "-" +
                                    String.format("%02d", dt.getMonthValue());
                            monthlyStats.merge(monthKey, count, Long::sum);
                        }
                    }

                    long total = monthlyStats.values().stream()
                            .mapToLong(Long::longValue)
                            .sum();

                    if (total == 0) return null;

                    Map<String, Object> result = new HashMap<>();
                    result.put("user", user);
                    result.put("stats", monthlyStats); // уже отсортирован
                    result.put("total", total);

                    return result;
                })
                .filter(Objects::nonNull)
                .sorted((a, b) -> Long.compare(
                        (long) b.get("total"),
                        (long) a.get("total")
                ))
                .collect(Collectors.toList());
    }

    private long countSeparateYa(String text) {
        if (text == null) return 0;
        String[] words = text.split("[\\s.,!?;:]+");
        return Arrays.stream(words)
                .filter(word -> word.equalsIgnoreCase("я"))
                .count();
    }
}