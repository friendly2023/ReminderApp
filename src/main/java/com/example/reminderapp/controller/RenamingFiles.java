package com.example.reminderapp.controller;


import com.example.reminderapp.service.renamingFiles.RenamingFilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/renamingFiles")
@Slf4j
@RequiredArgsConstructor
//переименование фото 2025-06-29 22-20-09 -> 20250629_222009
public class RenamingFiles {
    private final RenamingFilesService renamingFilesService;
    //путь
    @Value("E:/Vladimir Alekseevich/Photo/Photos from 2023")
    private String storagePath;

    @PostMapping(value = "/1")
    public ResponseEntity<String> renamingFiles1() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        log.info("Времени начала работы: {}", start);

        String resultRenamingFiles = renamingFilesService.renamingFiles(storagePath);

        LocalDateTime end = LocalDateTime.now();
        log.info("Времени конца работы: {}", end);
        Duration duration = Duration.between(start, end);
        log.info("Затраченное время: {}", duration);

        return ResponseEntity.ok(resultRenamingFiles);
    }

    @PostMapping(value = "/2")
    public ResponseEntity<String> renamingFiles2() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        log.info("Времени начала работы: {}", start);

        String resultRenamingFiles = renamingFilesService.renamingFiles2(storagePath);

        LocalDateTime end = LocalDateTime.now();
        log.info("Времени конца работы: {}", end);
        Duration duration = Duration.between(start, end);
        log.info("Затраченное время: {}", duration);

        return ResponseEntity.ok(resultRenamingFiles);
    }
}
