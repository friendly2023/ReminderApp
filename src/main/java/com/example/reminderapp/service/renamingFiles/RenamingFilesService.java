package com.example.reminderapp.service.renamingFiles;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class RenamingFilesService {

    public String renamingFiles(String storagePath) throws IOException {
        int sum = 0;
        int corrected = 0;

        Path directory = Paths.get(storagePath);
        // Проверяем, существует ли папка
        if (!Files.exists(directory)) {
            throw new IOException("Directory not found: " + storagePath);
        }
        // Перебираем все файлы в папке
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path filePath : stream) {
                sum++;
                // Работаем только с файлами (игнорируем папки, если вдруг появятся)
                if (Files.isRegularFile(filePath)) {
                    String oldName = filePath.getFileName().toString();
                    // Создаём новое имя: убираем дефисы, пробел на подчеркивание, убираем двоеточия
                    String newName = oldName
                            .replace("-", "")
                            .replace(" ", "_")
                            .replace(":", "");

                    // Если имя изменилось, переименовываем
                    if (!oldName.equals(newName)) {
                        Path newFilePath = directory.resolve(newName);
                        Files.move(filePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);
                        corrected++;
                    }
                }
            }
        }
        return "Всего:" + sum + ";Переименовано:" + corrected;
    }

    public String renamingFiles2(String storagePath) throws IOException {
        int sum = 0;
        int corrected = 0;
        int skipped = 0;

        Path directory = Paths.get(storagePath);
        // Проверяем, существует ли папка
        if (!Files.exists(directory)) {
            throw new IOException("Directory not found: " + storagePath);
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path filePath : stream) {
                sum++;

                if (Files.isRegularFile(filePath)) {
                    String oldName = filePath.getFileName().toString();

                    if (oldName.matches("^(IMG|VID)_\\d{8}_\\d{6}\\..*$")) {
                        String newName = oldName.substring(4);

                        if (!oldName.equals(newName)) {
                            Path newFilePath = directory.resolve(newName);
                            Files.move(filePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);
                            corrected++;
                        }
                    } else {
                        skipped++;
                    }
                }
            }
        }
        return String.format("Всего:%d;Переименовано:%d;Пропущено:%d", sum, corrected, skipped);
    }
}
